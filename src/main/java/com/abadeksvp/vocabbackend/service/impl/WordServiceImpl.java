package com.abadeksvp.vocabbackend.service.impl;

import com.abadeksvp.vocabbackend.exceptions.ApiException;
import com.abadeksvp.vocabbackend.mapping.creator.WordCreator;
import com.abadeksvp.vocabbackend.mapping.mapper.WordToWordResponseMapper;
import com.abadeksvp.vocabbackend.mapping.updater.WordUpdater;
import com.abadeksvp.vocabbackend.model.api.word.request.ChangeWordStatusRequest;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.api.word.response.WordResponse;
import com.abadeksvp.vocabbackend.model.db.Word;
import com.abadeksvp.vocabbackend.repository.WordRepository;
import com.abadeksvp.vocabbackend.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final WordCreator wordCreator;
    private final WordUpdater wordUpdater;
    private final WordToWordResponseMapper toWordResponseMapper;

    public WordServiceImpl(WordRepository wordRepository,
                           WordCreator wordCreator, WordUpdater wordUpdater, WordToWordResponseMapper toWordResponseMapper) {
        this.wordRepository = wordRepository;
        this.wordCreator = wordCreator;
        this.wordUpdater = wordUpdater;
        this.toWordResponseMapper = toWordResponseMapper;
    }

    @Override
    public WordResponse createWord(UpsertWordRequest request) {
        Word word = wordCreator.create(request);
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }

    @Override
    public WordResponse updateWord(UpsertWordRequest request) {
        Word existingWord = wordRepository.findById(request.getId())
                .orElseThrow(() -> new ApiException("Word not found", HttpStatus.NOT_FOUND));
        Word word = wordUpdater.update(request, existingWord);
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }

    @Override
    public WordResponse changeWordStatus(ChangeWordStatusRequest request) {
        Word word = wordRepository.findById(request.getId())
                .orElseThrow(() -> new ApiException("Word not found", HttpStatus.NOT_FOUND));
        word.setStatus(request.getStatus());
        Word savedWord = wordRepository.save(word);
        return toWordResponseMapper.map(savedWord);
    }
}
