package com.abadeksvp.vocabbackend.repository;

import com.abadeksvp.vocabbackend.model.db.Language;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguageRepository extends MongoRepository<Language, UUID> {
}
