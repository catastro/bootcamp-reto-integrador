package com.bootcamp.channelpayment.repository;

import com.bootcamp.channelpayment.entity.ServiceType;
import com.bootcamp.channelpayment.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;


@Repository
@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {

    private final WebClient webClient;

    public PaymentRepositoryImpl(WebClient.Builder builder,
                                 @Value("${application.url.payments}") String url) {
        HttpClient client = HttpClient.create();
        this.webClient = builder.baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }


    @Override
    public Flux<ServiceType> findByChannel(String channel, String token) {
        return this.webClient.get().uri(uriBuilder -> uriBuilder
                .path("/services")
                .queryParam("channel", channel)
                .build()).header("Authorization", token)
                .retrieve()
                .bodyToFlux(ServiceType.class);
    }

    @Override
    public Mono<Transaction> save(Transaction transaction, String token) {
        return this.webClient.post().uri("/transactions")
                .header("Authorization", token)
                .body(Mono.just(transaction), Transaction.class)
                .retrieve()
                .bodyToMono(Transaction.class)
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                );
    }
}
