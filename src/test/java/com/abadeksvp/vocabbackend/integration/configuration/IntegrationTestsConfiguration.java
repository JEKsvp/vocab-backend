package com.abadeksvp.vocabbackend.integration.configuration;

import com.abadeksvp.vocabbackend.integration.helpers.TestUserManager;
import com.abadeksvp.vocabbackend.integration.helpers.TestUuidGenerator;
import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import com.abadeksvp.vocabbackend.service.UserService;
import com.abadeksvp.vocabbackend.service.UuidGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

@TestConfiguration
public class IntegrationTestsConfiguration {


    public static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2022, 9, 25, 22, 30, 40);

    @Bean
    public DateTimeGenerator dateTimeGenerator() {
        return () -> TEST_DATE_TIME;
    }

    @Bean
    public UuidGenerator uuidGenerator() {
        return new TestUuidGenerator();
    }

    @Bean
    public TestUserManager testUserManager(UserService userService, MockMvc mockMvc) {
        return new TestUserManager(userService, mockMvc);
    }
}
