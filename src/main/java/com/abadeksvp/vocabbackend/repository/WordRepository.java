package com.abadeksvp.vocabbackend.repository;

import com.abadeksvp.vocabbackend.model.db.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WordRepository extends MongoRepository<Word, UUID> {
}
