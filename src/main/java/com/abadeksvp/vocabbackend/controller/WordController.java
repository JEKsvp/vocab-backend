package com.abadeksvp.vocabbackend.controller;

import com.abadeksvp.vocabbackend.model.api.word.request.ChangeWordStatusRequest;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.api.word.response.WordResponse;
import com.abadeksvp.vocabbackend.service.WordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WordResponse createWord(@RequestBody @Valid UpsertWordRequest request) {
        return wordService.createWord(request);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WordResponse updateWord(@RequestBody @Valid UpsertWordRequest request) {
        return wordService.updateWord(request);
    }

    @PatchMapping(value = "/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WordResponse updateWord(@RequestBody @Valid ChangeWordStatusRequest request) {
        return wordService.changeWordStatus(request);
    }
}
