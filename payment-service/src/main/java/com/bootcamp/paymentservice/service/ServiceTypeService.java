package com.bootcamp.paymentservice.service;

import com.bootcamp.paymentservice.entity.ServiceType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceTypeService {

    Flux<ServiceType> findByChannel(String channel);

    Mono<ServiceType> save(ServiceType serviceType);
}
