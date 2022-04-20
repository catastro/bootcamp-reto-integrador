package com.bootcamp.channelpayment.service;

import com.bootcamp.channelpayment.entity.Favorite;
import reactor.core.publisher.Mono;

public interface FavoriteService {

    Mono<Favorite> save(Favorite favorite, String token);
}
