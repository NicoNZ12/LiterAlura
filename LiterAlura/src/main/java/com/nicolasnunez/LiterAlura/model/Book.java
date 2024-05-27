package com.nicolasnunez.LiterAlura.model;

import java.util.List;

public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private List<String> subjects;
    private List<String> languages;
    private long downloads;
}
