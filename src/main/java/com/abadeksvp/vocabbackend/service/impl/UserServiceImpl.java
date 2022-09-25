package com.abadeksvp.vocabbackend.service.impl;

import com.abadeksvp.vocabbackend.mapper.SignUpRequestAndUserMapper;
import com.abadeksvp.vocabbackend.mapper.UserToUserResponseMapper;
import com.abadeksvp.vocabbackend.model.api.SignUpRequest;
import com.abadeksvp.vocabbackend.model.api.UserResponse;
import com.abadeksvp.vocabbackend.model.db.User;
import com.abadeksvp.vocabbackend.repository.UserRepository;
import com.abadeksvp.vocabbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SignUpRequestAndUserMapper createUserRequestToUser;
    private final UserToUserResponseMapper userToUserResponse;

    public UserServiceImpl(UserRepository userRepository,
                           SignUpRequestAndUserMapper createUserRequestToUser,
                           UserToUserResponseMapper userToUserResponse) {
        this.userRepository = userRepository;
        this.createUserRequestToUser = createUserRequestToUser;
        this.userToUserResponse = userToUserResponse;
    }

    @Override
    public UserResponse signUp(SignUpRequest request) {
        User newUser = createUserRequestToUser.map(request);
        User createdUser = userRepository.save(newUser);
        return userToUserResponse.map(createdUser);
    }

    @Override
    public UserResponse getUsers() {
        return null;
    }
}
