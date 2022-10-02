package com.abadeksvp.vocabbackend.service.impl;

import com.abadeksvp.vocabbackend.exceptions.ApiException;
import com.abadeksvp.vocabbackend.mapper.UpsertWordRequestToWordMapper;
import com.abadeksvp.vocabbackend.mapper.WordToWordResponseMapper;
import com.abadeksvp.vocabbackend.model.api.word.request.ChangeWordStatusRequest;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.api.word.response.WordResponse;
import com.abadeksvp.vocabbackend.model.db.Word;
import com.abadeksvp.vocabbackend.repository.WordRepository;
import com.abadeksvp.vocabbackend.service.WordService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final UpsertWordRequestToWordMapper toWordMapper;
    private final WordToWordResponseMapper toWordResponseMapper;

    public WordServiceImpl(WordRepository wordRepository,
                           UpsertWordRequestToWordMapper toWordMapper,
                           WordToWordResponseMapper toWordResponseMapper) {
        this.wordRepository = wordRepository;
        this.toWordMapper = toWordMapper;
        this.toWordResponseMapper = toWordResponseMapper;
    }

    @Override
    public WordResponse createWord(UpsertWordRequest request) {
        Word word = toWordMapper.map(request);
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }

    @Override
    public WordResponse updateWord(UpsertWordRequest request) {
        Word word = toWordMapper.map(request);
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }

    @Override
    public WordResponse changeWordStatus(ChangeWordStatusRequest request) {
        Word word = wordRepository.findById(request.getId())
                .orElseThrow(() -> new ApiException(MessageFormat.format("Word with id {0} not found", request.getId())));
        word.setStatus(request.getStatus());
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }
}
