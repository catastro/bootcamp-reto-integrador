package com.bootcamp.paymentservice.handler;

import com.bootcamp.paymentservice.entity.ServiceType;
import com.bootcamp.paymentservice.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ServiceTypeHandler {

    @Autowired
    private ServiceTypeService serviceTypeService;

    public Mono<ServerResponse> findByChannel(ServerRequest request){
        var channel = request.queryParam("channel").get();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceTypeService.findByChannel(channel), ServiceType.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        var serviceTypeInput = request.bodyToMono(ServiceType.class);

        return serviceTypeInput
                .flatMap(service -> this.serviceTypeService.save(service))
                .flatMap(service -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(service), ServiceType.class));
    }
}
