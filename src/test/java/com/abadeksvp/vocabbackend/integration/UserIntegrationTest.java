package com.abadeksvp.vocabbackend.integration;

import com.abadeksvp.vocabbackend.integration.configuration.IntegrationTestsConfiguration;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = {UserIntegrationTest.Initializer.class})
@Import(IntegrationTestsConfiguration.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.4");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validSignUpTest() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResource("/request/sign-up-request.json"), Charset.defaultCharset());
        String expectedResponse = IOUtils.toString(getClass().getResource("/response/sign-up-response.json"), Charset.defaultCharset());
        String actualresponse = mockMvc.perform(post("/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualresponse, excludeFields("id"));
    }

    @NotNull
    private CustomComparator excludeFields(String... fields) {
        Customization[] customizations = Arrays.stream(fields)
                .map(field -> new Customization(field, (o1, o2) -> true))
                .toArray(Customization[]::new);
        return new CustomComparator(JSONCompareMode.NON_EXTENSIBLE, customizations);
    }

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
