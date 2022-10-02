package com.abadeksvp.vocabbackend.mapper;

import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.db.Word;
import com.abadeksvp.vocabbackend.repository.WordRepository;
import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import com.abadeksvp.vocabbackend.service.UuidGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpsertWordRequestToWordMapper implements Mapper<UpsertWordRequest, Word> {

    private final UuidGenerator uuidGenerator;
    private final UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper;
    private final WordRepository wordRepository;
    private final DateTimeGenerator dateTimeGenerator;


    public UpsertWordRequestToWordMapper(UuidGenerator uuidGenerator,
                                         UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper,
                                         WordRepository wordRepository, DateTimeGenerator dateTimeGenerator) {
        this.uuidGenerator = uuidGenerator;
        this.toDefinitionMapper = toDefinitionMapper;
        this.wordRepository = wordRepository;
        this.dateTimeGenerator = dateTimeGenerator;
    }

    @Override
    public Word map(UpsertWordRequest request) {
        LocalDateTime now = dateTimeGenerator.now();
        Optional<Word> existingWord = Optional.ofNullable(request.getId())
                .flatMap(wordRepository::findById);
        return Word.builder()
                .id(Optional.ofNullable(request.getId()).orElse(uuidGenerator.generate()))
                .title(request.getTitle())
                .transcription(request.getTranscription())
                .part(request.getPart())
                .status(request.getStatus())
                .definitions(toDefinitionMapper.mapAll(request.getDefinitions()))
                .createDate(existingWord.map(Word::getCreateDate).orElse(now))
                .lastUpdateDate(now)
                .build();
    }
}
