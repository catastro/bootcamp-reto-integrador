package com.bootcamp.paymentservice.service.impl;

import com.bootcamp.paymentservice.entity.ServiceType;
import com.bootcamp.paymentservice.exception.BaseException;
import com.bootcamp.paymentservice.repository.ServiceTypeRepository;
import com.bootcamp.paymentservice.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public Flux<ServiceType> findByChannel(String channel) {
        return serviceTypeRepository.findByChannel(channel)
                .switchIfEmpty(Flux.error(new BaseException(HttpStatus.NOT_FOUND,"Servicios no encontrados por el canal")));
    }

    @Override
    public Mono<ServiceType> save(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }
}
