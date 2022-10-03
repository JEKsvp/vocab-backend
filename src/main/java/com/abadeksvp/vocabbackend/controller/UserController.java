package com.abadeksvp.vocabbackend.controller;

import com.abadeksvp.vocabbackend.exceptions.ApiException;
import com.abadeksvp.vocabbackend.model.api.UserResponse;
import com.abadeksvp.vocabbackend.security.SecurityUtils;
import com.abadeksvp.vocabbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/current-user")
    public UserResponse getCurrentUser() {
        String userName = SecurityUtils.getCurrentUserName();
        return userService.findByUserName(userName)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    }
}
