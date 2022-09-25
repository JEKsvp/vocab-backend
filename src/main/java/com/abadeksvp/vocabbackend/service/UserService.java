package com.abadeksvp.vocabbackend.service;

import com.abadeksvp.vocabbackend.model.api.SignUpRequest;
import com.abadeksvp.vocabbackend.model.api.UserResponse;

public interface UserService {

    UserResponse signUp(SignUpRequest user);
    UserResponse getUsers();
}
