package com.bootcamp.channelpayment.repository;

import com.bootcamp.channelpayment.entity.ServiceType;
import com.bootcamp.channelpayment.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

    Flux<ServiceType> findByChannel(String channel, String token);

    Mono<Transaction> save(Transaction transaction, String token);
}
