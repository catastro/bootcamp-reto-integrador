package com.bootcamp.channelpayment.repository;

import com.bootcamp.channelpayment.entity.Favorite;
import com.bootcamp.channelpayment.entity.Transaction;
import com.bootcamp.channelpayment.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{

    private final WebClient webClient;

    public FavoriteRepositoryImpl(WebClient.Builder builder,
                                  @Value("${application.url.favorites}") String url) {
        HttpClient client = HttpClient.create();
        this.webClient = builder.baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();

    }


    @Override
    public Mono<Favorite> save(Favorite favorite, String token) {
        return this.webClient.post().uri("/favorites")
                .header("Authorization", token)
                .body(Mono.just(favorite), Favorite.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response-> {
                    return response.bodyToMono(BaseException.class)
                            .flatMap(Mono::error);
                })
                .bodyToMono(Favorite.class);
    }
}
