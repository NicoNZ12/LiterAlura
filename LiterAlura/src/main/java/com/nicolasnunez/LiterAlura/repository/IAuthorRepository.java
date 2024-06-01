package com.nicolasnunez.LiterAlura.repository;

import com.nicolasnunez.LiterAlura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
    Author findAuthorsByName(String name);
    @Query(value = "SELECT * FROM authors WHERE :year >= birth_year AND :year <= death_year", nativeQuery = true)
    List<Author> findAuthorBetweenYear(int year);

}
