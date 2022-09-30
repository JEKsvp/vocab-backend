package com.abadeksvp.vocabbackend.mapper;

import com.abadeksvp.vocabbackend.model.api.SignUpRequest;
import com.abadeksvp.vocabbackend.model.db.User;
import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignUpRequestAndUserMapper implements Mapper<SignUpRequest, User>{

    private final DateTimeGenerator dateTimeGenerator;
    private final PasswordEncoder passwordEncoder;

    public SignUpRequestAndUserMapper(DateTimeGenerator dateTimeGenerator) {
        this.dateTimeGenerator = dateTimeGenerator;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User map(SignUpRequest signUpRequest) {
        LocalDateTime now = dateTimeGenerator.now();
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        return User.builder()
                .username(signUpRequest.getUsername())
                .password(encodedPassword)
                .createDate(now)
                .lastUpdateDate(now)
                .build();
    }
}
