package com.bootcamp.channelpayment.service.impl;

import com.bootcamp.channelpayment.entity.ServiceType;
import com.bootcamp.channelpayment.repository.PaymentRepository;
import com.bootcamp.channelpayment.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

   @Autowired
   private PaymentRepository serviceTypeRepository;

    @Override
    public Flux<ServiceType> findByChannel(String channel, String token) {
        return serviceTypeRepository.findByChannel(channel, token);
    }
}
