package com.bootcamp.channelpayment.repository;

import com.bootcamp.channelpayment.entity.Favorite;
import reactor.core.publisher.Mono;

public interface FavoriteRepository {

    Mono<Favorite> save(Favorite favorite, String token);
}
