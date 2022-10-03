package com.abadeksvp.vocabbackend.mapping.creator;

import com.abadeksvp.vocabbackend.mapping.mapper.UpsertDefinitionRequestToDefinitionMapper;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.db.Word;
import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import com.abadeksvp.vocabbackend.service.UuidGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WordCreator implements Creator<UpsertWordRequest, Word> {

    private final UuidGenerator uuidGenerator;
    private final UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper;
    private final DateTimeGenerator dateTimeGenerator;

    public WordCreator(UuidGenerator uuidGenerator,
                       UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper,
                       DateTimeGenerator dateTimeGenerator) {
        this.uuidGenerator = uuidGenerator;
        this.toDefinitionMapper = toDefinitionMapper;
        this.dateTimeGenerator = dateTimeGenerator;
    }

    @Override
    public Word create(UpsertWordRequest request) {
        LocalDateTime now = dateTimeGenerator.now();
        return Word.builder()
                .id(uuidGenerator.generate())
                .title(request.getTitle())
                .transcription(request.getTranscription())
                .part(request.getPart())
                .status(request.getStatus())
                .definitions(toDefinitionMapper.mapAll(request.getDefinitions()))
                .createDate(now)
                .lastUpdateDate(now)
                .build();
    }
}
