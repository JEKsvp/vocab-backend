package com.abadeksvp.vocabbackend.service;

import com.abadeksvp.vocabbackend.model.api.word.request.ChangeWordStatusRequest;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.api.word.response.WordResponse;

public interface WordService {

    WordResponse createWord(UpsertWordRequest request);
    WordResponse updateWord(UpsertWordRequest request);
    WordResponse changeWordStatus(ChangeWordStatusRequest request);
}
