package com.nicolasnunez.LiterAlura.entity;

import com.nicolasnunez.LiterAlura.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer birthYear;

    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(AuthorDTO authorDTO){
        this.name = authorDTO.authorName();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
    }

    @Override
    public String toString() {
        return "----- Autor -----" +
                "\n Nombre: " + name +
                "\n Fecha de Nacimiento: " + birthYear +
                "\n Fecha de Fallecimiento: " + deathYear +
                "\n Libros: " + books.stream().map(b -> b.getTitle()).collect(Collectors.toList()) +
                "\n---------------\n";
    }
}
