package com.bootcamp.channelpayment.service.impl;

import com.bootcamp.channelpayment.entity.Favorite;
import com.bootcamp.channelpayment.repository.FavoriteRepository;
import com.bootcamp.channelpayment.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public Mono<Favorite> save(Favorite favorite, String token) {
        return favoriteRepository.save(favorite, token);
    }
}
