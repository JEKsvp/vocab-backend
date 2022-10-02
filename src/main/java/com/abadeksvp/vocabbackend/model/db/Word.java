package com.abadeksvp.vocabbackend.model.db;

import com.abadeksvp.vocabbackend.model.WordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
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
    private WordStatus status;
    private List<Definition> definitions;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
