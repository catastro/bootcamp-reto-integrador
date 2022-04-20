package com.bootcamp.channelpayment.service;

import com.bootcamp.channelpayment.entity.ServiceType;
import reactor.core.publisher.Flux;

public interface ServiceTypeService {

    Flux<ServiceType> findByChannel(String channel, String token);
}
