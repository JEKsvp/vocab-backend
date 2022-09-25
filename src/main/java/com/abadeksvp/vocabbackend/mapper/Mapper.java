package com.abadeksvp.vocabbackend.mapper;

public interface Mapper <Source, Target>{

    Target map(Source source);
}
