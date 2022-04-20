package com.bootcamp.channelpayment.handler;

import com.bootcamp.channelpayment.entity.Transaction;
import com.bootcamp.channelpayment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {

    @Autowired
    private TransactionService transactionService;

    public Mono<ServerResponse> save(ServerRequest request) {
        var transactionInput =request.bodyToMono(Transaction.class);
        var tokenHeader = request.headers().header("Authorization").get(0);

        return transactionInput
                .flatMap(transaction -> this.transactionService.save(transaction, tokenHeader))
                .flatMap(transaction -> ServerResponse.ok().body(Mono.just(transaction), Transaction.class));
    }
}
