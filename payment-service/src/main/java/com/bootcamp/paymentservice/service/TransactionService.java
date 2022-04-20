package com.bootcamp.paymentservice.service;

import com.bootcamp.paymentservice.entity.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> save(Transaction transaction);
}
