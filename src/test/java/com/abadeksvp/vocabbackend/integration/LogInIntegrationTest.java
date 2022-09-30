package com.abadeksvp.vocabbackend.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.abadeksvp.vocabbackend.integration.configuration.helpers.TestUserManager.DEFAULT_TEST_PASSWORD;
import static com.abadeksvp.vocabbackend.integration.configuration.helpers.TestUserManager.DEFAULT_TEST_USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogInIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void validLogInTest() throws Exception {
        testUserManager.signUpDefaultTestUser();
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", DEFAULT_TEST_USERNAME)
                        .param("password", DEFAULT_TEST_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"));
    }

    @Test
    public void invalidPasswordInTest() throws Exception {
        testUserManager.signUpDefaultTestUser();
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", DEFAULT_TEST_USERNAME)
                        .param("password", "wrong password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login.html?error=true"));
    }
}
