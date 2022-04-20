package com.bootcamp.favoriteservice.service;

import com.bootcamp.favoriteservice.entity.Favorite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavoriteService {

    Mono<Favorite> save(Favorite favorite);

    Flux<Favorite> findAll();
}
