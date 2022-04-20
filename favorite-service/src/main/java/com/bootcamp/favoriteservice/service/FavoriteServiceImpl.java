package com.bootcamp.favoriteservice.service;

import com.bootcamp.favoriteservice.core.exception.FavoriteBaseException;
import com.bootcamp.favoriteservice.entity.Favorite;
import com.bootcamp.favoriteservice.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public Mono<Favorite> save(Favorite favorite) {
        return favoriteRepository.existsByServiceId(favorite.getServiceId())
                .flatMap(exists -> !exists ? favoriteRepository.save(favorite) :
                        Mono.error(new FavoriteBaseException(HttpStatus.BAD_REQUEST, "Favorito ya existe")));
    }

    @Override
    public Flux<Favorite> findAll() {
        return favoriteRepository.findAll();
    }
}
