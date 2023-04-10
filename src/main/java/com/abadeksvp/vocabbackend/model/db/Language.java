package com.abadeksvp.vocabbackend.model.db;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Language {

    private UUID id;

    private String name;
}
