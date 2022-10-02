package com.abadeksvp.vocabbackend.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<Source, Target> {

    Target map(Source source);

    default List<Target> mapAll(Collection<Source> sources) {
        return sources.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
