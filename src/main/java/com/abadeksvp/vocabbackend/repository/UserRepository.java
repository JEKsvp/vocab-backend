package com.abadeksvp.vocabbackend.repository;

import com.abadeksvp.vocabbackend.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String > {
}
