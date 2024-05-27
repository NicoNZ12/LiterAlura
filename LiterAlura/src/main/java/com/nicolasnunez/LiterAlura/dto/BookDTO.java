package com.nicolasnunez.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nicolasnunez.LiterAlura.model.Author;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("title")
        String title,
        @JsonAlias("authors")
        List<AuthorDTO> authors,
        @JsonAlias("subjects")
        List<String> subjects,
        @JsonAlias("languages")
        List<String> languages,
        @JsonAlias("download_count")
        long downloads
) {
}
