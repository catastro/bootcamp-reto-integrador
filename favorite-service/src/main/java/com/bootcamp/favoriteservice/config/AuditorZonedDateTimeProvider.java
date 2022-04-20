package com.bootcamp.favoriteservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class AuditorZonedDateTimeProvider implements DateTimeProvider {

    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(ZonedDateTime.now(ZoneId.of(timeZone)));
    }
}
