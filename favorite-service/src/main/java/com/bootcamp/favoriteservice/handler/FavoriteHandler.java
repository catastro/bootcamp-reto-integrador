package com.bootcamp.favoriteservice.handler;

import com.bootcamp.favoriteservice.entity.Favorite;
import com.bootcamp.favoriteservice.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FavoriteHandler {

    @Autowired
    FavoriteService favoriteService;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Favorite.class)
                .flatMap(favorite -> this.favoriteService.save(favorite))
                .flatMap(favorite -> ServerResponse.ok().body(Mono.just(favorite), Favorite.class));
    }
}
