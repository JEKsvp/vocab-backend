package com.abadeksvp.vocabbackend.integration;

import com.abadeksvp.vocabbackend.integration.configuration.IntegrationTestsConfiguration;
import com.abadeksvp.vocabbackend.integration.configuration.helpers.TestUserManager;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
@Import(IntegrationTestsConfiguration.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public abstract class AbstractIntegrationTest {

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.4");

    static {
        postgres.start();
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TestUserManager testUserManager;

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.hikari.username=" + postgres.getUsername(),
                    "spring.datasource.hikari.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
