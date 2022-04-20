package com.bootcamp.favoriteservice.repository;

import com.bootcamp.favoriteservice.entity.Favorite;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteRepository extends ReactiveCrudRepository<Favorite, Long> {

    Mono<Boolean> existsByServiceId(String serviceId);
}
