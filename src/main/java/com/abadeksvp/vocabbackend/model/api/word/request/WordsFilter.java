package com.abadeksvp.vocabbackend.model.api.word.request;

import com.abadeksvp.vocabbackend.model.WordStatus;
import com.abadeksvp.vocabbackend.model.api.PageableFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WordsFilter extends PageableFilter {
    @Nullable
    private String q;

    @NotNull
    private WordStatus status;
}
