package com.abadeksvp.vocabbackend.mapping.updater;

import com.abadeksvp.vocabbackend.mapping.mapper.UpsertDefinitionRequestToDefinitionMapper;
import com.abadeksvp.vocabbackend.model.api.word.request.UpsertWordRequest;
import com.abadeksvp.vocabbackend.model.db.Word;
import com.abadeksvp.vocabbackend.service.DateTimeGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WordUpdater implements Updater<UpsertWordRequest, Word>{

    private final UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper;
    private final DateTimeGenerator dateTimeGenerator;

    public WordUpdater(UpsertDefinitionRequestToDefinitionMapper toDefinitionMapper, DateTimeGenerator dateTimeGenerator) {
        this.toDefinitionMapper = toDefinitionMapper;
        this.dateTimeGenerator = dateTimeGenerator;
    }

    @Override
    public Word update(UpsertWordRequest request, Word existing) {
        LocalDateTime now = dateTimeGenerator.now();
        return Word.builder()
                .id(existing.getId())
                .title(request.getTitle())
                .transcription(request.getTranscription())
                .part(request.getPart())
                .status(request.getStatus())
                .definitions(toDefinitionMapper.mapAll(request.getDefinitions()))
                .createDate(existing.getCreateDate())
                .lastUpdateDate(now)
                .build();
    }
}
