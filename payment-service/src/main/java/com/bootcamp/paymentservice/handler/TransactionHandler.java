package com.bootcamp.paymentservice.handler;

import com.bootcamp.paymentservice.entity.Transaction;
import com.bootcamp.paymentservice.exception.BaseException;
import com.bootcamp.paymentservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.StringJoiner;

@Component
public class TransactionHandler {

    private final Validator validator;

    @Autowired
    private TransactionService transactionService;

    public TransactionHandler(Validator validator) {
        this.validator = validator;
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        var transactionInput =request.bodyToMono(Transaction.class);

        return transactionInput
                .filter(this::validate)
                .flatMap(transaction -> this.transactionService.save(transaction))
                .flatMap(transaction -> ServerResponse.ok().body(Mono.just(transaction), Transaction.class));
    }

    private boolean validate(Transaction transaction) {
        Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transaction);

        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringJoiner stringJoiner = new StringJoiner(" ");
            constraintViolations.forEach(
                    loginModelConstraintViolation ->
                            stringJoiner
                                    .add(loginModelConstraintViolation.getPropertyPath().toString())
                                    .add(":")
                                    .add(loginModelConstraintViolation.getMessage()));
            throw new BaseException(HttpStatus.BAD_REQUEST,stringJoiner.toString());
        }

        return true;
    }
}
