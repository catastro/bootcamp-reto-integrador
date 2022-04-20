package com.bootcamp.paymentservice.repository;

import com.bootcamp.paymentservice.entity.ServiceType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ServiceTypeRepository extends ReactiveMongoRepository<ServiceType, String> {

    Flux<ServiceType> findByChannel(String channel);
}
