package com.bootcamp.channelpayment.handler;

import com.bootcamp.channelpayment.entity.Favorite;
import com.bootcamp.channelpayment.service.FavoriteService;
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
        var tokenHeader = request.headers().header("Authorization").get(0);

        return request.bodyToMono(Favorite.class)
                .flatMap(favorite -> this.favoriteService.save(favorite, tokenHeader))
                .flatMap(favorite -> ServerResponse.ok().body(Mono.just(favorite), Favorite.class));
    }
}
