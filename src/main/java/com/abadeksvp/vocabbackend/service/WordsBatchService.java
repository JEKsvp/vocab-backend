package com.abadeksvp.vocabbackend.service;

import com.abadeksvp.vocabbackend.model.api.word.response.WordResponse;

import java.util.List;

public interface WordsBatchService {

    void generate(int size);
    List<WordResponse> getBatch();
}
