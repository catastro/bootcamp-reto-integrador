package com.bootcamp.paymentservice.repository;

import com.bootcamp.paymentservice.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

}
