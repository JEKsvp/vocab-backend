BEGIN;


CREATE TABLE IF NOT EXISTS users
(
    id uuid NOT NULL,
    username character varying(255) NOT NULL UNIQUE,
    password character varying(255) NOT NULL,
    create_date timestamp NOT NULL,
    last_update_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS words
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    title character varying(255) NOT NULL,
    transcription character varying(255),
    part character varying(255),
    create_date timestamp NOT NULL,
    last_update_date timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_words_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS definitions
(
    id uuid NOT NULL,
    word_id uuid NOT NULL,
    definition text NOT NULL,
    create_date timestamp NOT NULL,
    last_update_date timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_definitions_words FOREIGN KEY (word_id) REFERENCES words(id)
);

CREATE TABLE IF NOT EXISTS examples
(
    id uuid NOT NULL,
    definition_id uuid NOT NULL,
    example text,
    create_date timestamp NOT NULL,
    last_update_date timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_examples_definitions FOREIGN KEY (definition_id) REFERENCES definitions(id)
);

END;