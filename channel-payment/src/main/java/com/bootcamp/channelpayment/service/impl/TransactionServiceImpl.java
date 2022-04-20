package com.bootcamp.channelpayment.service.impl;

import com.bootcamp.channelpayment.entity.Transaction;
import com.bootcamp.channelpayment.repository.PaymentRepository;
import com.bootcamp.channelpayment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Mono<Transaction> save(Transaction transaction, String token) {
        return paymentRepository.save(transaction, token);
    }
}
