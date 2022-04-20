package com.bootcamp.paymentservice.service.impl;

import com.bootcamp.paymentservice.entity.Transaction;
import com.bootcamp.paymentservice.exception.BaseException;
import com.bootcamp.paymentservice.repository.ServiceTypeRepository;
import com.bootcamp.paymentservice.repository.TransactionRepository;
import com.bootcamp.paymentservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        transaction.setCreateDate(new Date());
        return serviceTypeRepository.findById(transaction.getServiceId())
                .switchIfEmpty(Mono.error(new BaseException(HttpStatus.NOT_FOUND, "Servicio no encontrado")))
                .flatMap(service -> transactionRepository.save(transaction));
    }
}
