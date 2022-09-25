package com.abadeksvp.vocabbackend.integration.configuration;

import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import com.abadeksvp.vocabbackend.service.UuidGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@TestConfiguration
public class IntegrationTestsConfiguration {

    public static final String TEST_UUID = "79ca8d24-f686-472a-b6f8-e40b5aacfc7e";
    public static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2022, 9, 25, 22, 30, 40);

    @Bean
    public DateTimeGenerator dateTimeGenerator() {
        return () -> TEST_DATE_TIME;
    }

    @Bean
    public UuidGenerator uuidGenerator() {
        return () -> UUID.fromString(TEST_UUID);
    }
}
