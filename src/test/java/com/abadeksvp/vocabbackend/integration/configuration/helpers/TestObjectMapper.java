package com.abadeksvp.vocabbackend.integration.configuration.helpers;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class TestObjectMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getInstance(){
        return OBJECT_MAPPER;
    }
}
