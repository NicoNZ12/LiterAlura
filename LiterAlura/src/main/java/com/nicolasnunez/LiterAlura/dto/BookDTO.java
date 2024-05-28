package com.nicolasnunez.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("title")
        String title,
        @JsonAlias("authors")
        List<AuthorDTO> authors,
        @JsonAlias("languages")
        List<String> languages,
        @JsonAlias("download_count")
        long downloads
) {
        @Override
        public String toString() {
                return "\n----- Libro -----" +
                        "\n Titulo: " + title +
                        "\n Autor: " + authors.get(0) +
                        "\n Idioma: " + languages.get(0).toUpperCase() +
                        "\n Número de descargas: " + downloads +
                        "\n-----------------\n";
        }
}
