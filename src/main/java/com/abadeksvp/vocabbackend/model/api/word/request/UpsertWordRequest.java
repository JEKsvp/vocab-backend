package com.abadeksvp.vocabbackend.model.api.word.request;

import com.abadeksvp.vocabbackend.model.WordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertWordRequest {
    private UUID id;
    @NotEmpty
    private String title;
    private String transcription;
    private String part;
    @NotNull
    private WordStatus status;
    @NotEmpty
    private List<UpsertDefinitionRequest> definitions = new ArrayList<>();
}
