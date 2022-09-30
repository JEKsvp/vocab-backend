package com.abadeksvp.vocabbackend.integration.configuration.helpers;

import com.abadeksvp.vocabbackend.model.api.SignUpRequest;
import com.abadeksvp.vocabbackend.model.api.UserResponse;
import com.abadeksvp.vocabbackend.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.validation.Valid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUserManager {

    private final UserService userService;
    private final MockMvc mockMvc;
    public static final String DEFAULT_TEST_USERNAME = "test_username";
    public static final String DEFAULT_TEST_PASSWORD = "test_password";

    private static final SignUpRequest DEFAULT_SIGNUP = SignUpRequest.builder()
            .username(DEFAULT_TEST_USERNAME)
            .password(DEFAULT_TEST_PASSWORD)
            .build();

    public TestUserManager(UserService userService, MockMvc mockMvc) {
        this.userService = userService;
        this.mockMvc = mockMvc;
    }

    @SneakyThrows
    public UserResponse signUp(@Valid SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    public UserResponse signUpDefaultTestUser() {
        return userService.signUp(DEFAULT_SIGNUP);
    }

    @SneakyThrows
    public void loginWithDefaultUser(){
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", DEFAULT_TEST_USERNAME)
                        .param("password", DEFAULT_TEST_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"))
                .andReturn();
        System.out.println();
    }
}
