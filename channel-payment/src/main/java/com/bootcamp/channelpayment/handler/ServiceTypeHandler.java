package com.bootcamp.channelpayment.handler;

import com.bootcamp.channelpayment.entity.ServiceType;
import com.bootcamp.channelpayment.service.ServiceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ServiceTypeHandler {

    @Autowired
    private ServiceTypeService serviceTypeService;

    public Mono<ServerResponse> findByChannel(ServerRequest request){
        var channel = request.queryParam("channel").get();
        var tokenHeader = request.headers().header("Authorization").get(0);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceTypeService.findByChannel(channel, tokenHeader), ServiceType.class);
    }
}

