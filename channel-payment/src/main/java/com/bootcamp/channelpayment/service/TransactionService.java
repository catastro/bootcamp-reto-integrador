package com.bootcamp.channelpayment.service;

import com.bootcamp.channelpayment.entity.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> save(Transaction transaction, String token);
}
