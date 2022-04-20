package com.bootcamp.favoriteservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing(dateTimeProviderRef = "dateTimeProvider")
public class AuditorDBConfig {

    @Bean
    public ReactiveAuditorAware<String> myAuditorProvider() {
        return new AuditorAwareImpl();
    }

    @Bean
    public DateTimeProvider dateTimeProvider(){
        return new AuditorZonedDateTimeProvider();
    }
}
