package com.bootcamp.paymentservice.routes;

import com.bootcamp.paymentservice.handler.ServiceTypeHandler;
import com.bootcamp.paymentservice.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> serviceRoutes(ServiceTypeHandler handler){
        return RouterFunctions.nest(path("/services"),
                RouterFunctions
                        .route(GET(""), handler::findByChannel)
                        .andRoute(POST("").and(accept(MediaType.APPLICATION_JSON)), handler::save));
    }

    @Bean
    public RouterFunction<ServerResponse> transactionRoute(TransactionHandler handler){
        return RouterFunctions.nest(path("/transactions"),
                RouterFunctions.route(POST("").and(accept(MediaType.APPLICATION_JSON)), handler::save));
    }

}
