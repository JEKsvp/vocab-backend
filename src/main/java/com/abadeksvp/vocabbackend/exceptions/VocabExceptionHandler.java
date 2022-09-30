package com.abadeksvp.vocabbackend.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class VocabExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> messages = ex.getFieldErrors().stream()
                .map(fieldError -> MessageFormat.format("Validation error. Field: {0}; message: {1}.", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ErrorResponse body = ErrorResponse.builder()
                .messages(messages)
                .build();
        return new ResponseEntity<>(body, headers, status);
    }
}
