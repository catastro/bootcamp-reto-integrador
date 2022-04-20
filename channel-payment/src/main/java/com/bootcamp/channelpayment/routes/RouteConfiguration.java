package com.bootcamp.channelpayment.routes;

import com.bootcamp.channelpayment.handler.FavoriteHandler;
import com.bootcamp.channelpayment.handler.ServiceTypeHandler;
import com.bootcamp.channelpayment.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> serviceRoutes(ServiceTypeHandler handler){
        return RouterFunctions.nest(path("/services"),
                RouterFunctions
                        .route(GET(""), handler::findByChannel));
    }

    @Bean
    public RouterFunction<ServerResponse> transactionRoute(TransactionHandler handler){
        return RouterFunctions.nest(path("/transactions"),
                RouterFunctions.route(POST("").and(accept(MediaType.APPLICATION_JSON)), handler::save));
    }

    @Bean
    public RouterFunction<ServerResponse> favoriteRoute(FavoriteHandler handler){
        return RouterFunctions.nest(path("/favorites"),
                RouterFunctions.route(POST("").and(accept(MediaType.APPLICATION_JSON)), handler::save));
    }

}
