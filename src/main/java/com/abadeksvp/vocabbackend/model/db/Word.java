package com.abadeksvp.vocabbackend.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    private UUID id;

    private String title;

    private String transcription;

    private String part;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdateDate;
}
