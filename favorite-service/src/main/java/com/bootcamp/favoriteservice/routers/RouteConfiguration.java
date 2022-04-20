package com.bootcamp.favoriteservice.routers;

import com.bootcamp.favoriteservice.entity.Favorite;
import com.bootcamp.favoriteservice.handler.FavoriteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> favoriteRoutes(FavoriteHandler favoriteHandler){
        return RouterFunctions.nest(RequestPredicates.path("/favorites"),
                RouterFunctions
                        .route(POST("").and(contentType(MediaType.APPLICATION_JSON)), favoriteHandler::save)
        );
    }
}
