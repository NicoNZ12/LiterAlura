package com.nicolasnunez.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDTO(
        @JsonAlias("name")
        String authorName,

        @JsonAlias("birth_year")
        int birthYear,

        @JsonAlias("death_year")
        int deathYear
) {
}
