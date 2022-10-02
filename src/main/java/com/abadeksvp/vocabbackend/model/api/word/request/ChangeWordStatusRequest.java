package com.abadeksvp.vocabbackend.model.api.word.request;

import com.abadeksvp.vocabbackend.model.WordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeWordStatusRequest {
    private UUID id;
    private WordStatus status;
}
