package com.nicolasnunez.LiterAlura.main;


import com.nicolasnunez.LiterAlura.dto.AuthorDTO;
import com.nicolasnunez.LiterAlura.dto.BookDTO;
import com.nicolasnunez.LiterAlura.dto.JsonDTO;

import com.nicolasnunez.LiterAlura.entity.Author;
import com.nicolasnunez.LiterAlura.entity.Book;
import com.nicolasnunez.LiterAlura.repository.IAuthorRepository;
import com.nicolasnunez.LiterAlura.repository.IbookRepository;
import com.nicolasnunez.LiterAlura.service.ConnectionAPI;
import com.nicolasnunez.LiterAlura.service.DataConvertion;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private ConnectionAPI cnx = new ConnectionAPI();
    private DataConvertion dataConvertion = new DataConvertion();
    private static final String API_URL = "https://gutendex.com/books";

    private IbookRepository bookRepository;
    private IAuthorRepository authorRepository;

    public Main(IbookRepository bookRepository, IAuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private int opc = -1;

    public void displayMenu(){
        while(opc != 0){
            System.out.println("---------- Menú ---------- ");
            System.out.println("""
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    0. Salir
                    """);

            try{
                opc = Integer.parseInt(sc.nextLine());

                switch(opc){
                    case 1:
                        getBook();
                        break;
                    case 2:
                        System.out.println("----- Libros Registrados -----\n");
                        getAllListedBooks();
                        break;
                    case 3:
                        System.out.println("----- Autores Registrados -----\n");
                        getListedAuthors();
                        break;
                    case 4:
                        getAuthorBetweenYears();
                        break;
                    case 5:
                        getBooksByLanguage();
                        break;
                    case 0:
                        System.out.println("Gracias por usar LiterAlura!\n");
                        break;
                    default:
                        System.out.println("Opción elegida incorrecta. Elija nuevamente.\n");
                }
            }catch(NumberFormatException e){
                System.out.println("Debes seleccionar un número.");
            }
        }
    }

    private void getBook() {
        System.out.println("Escriba el nombre del libro: ");
        String bookName = sc.nextLine();

        String json = cnx.getData(API_URL + "/?search=" + bookName.replace(" ", "+"));
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);

        Optional<Book> books = results.bookResults().stream()
                .findFirst()
                .map(b -> new Book(b));

        if (books.isPresent()) {
            Book book = books.get();

            if (book.getAuthor() != null) {
                Author author = authorRepository.findAuthorsByName(book.getAuthor().getName());

                if (author == null) {
                    // Crear y guardar un nuevo autor si no existe
                    Author newAuthor = book.getAuthor();
                    author = authorRepository.save(newAuthor);
                }

                try {
                    // Asociar el autor existente con el libro
                    book.setAuthor(author);
                    bookRepository.save(book);
                    System.out.println(book);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("El libro ya se encuentra registrado en la base de datos.");
                }
            }
        } else {
            System.out.println("No se encontró el libro: " + bookName);
        }
    }
    private void getAllListedBooks(){
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void getListedAuthors(){
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void getAuthorBetweenYears(){
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar: ");
        int year = Integer.parseInt(sc.nextLine());

        List<Author> authors = authorRepository.findAuthorBetweenYear(year);
        authors.forEach(System.out::println);

    }

    private void getBooksByLanguage(){
        System.out.println("Ingrese el idioma que desea buscar: ");
        System.out.println("""
                es -> Español
                en -> Inglés
                fr -> Francés
                pt -> Portugés
                """);
        String language = sc.nextLine();

        List<Book> books = bookRepository.findBookByLanguage(language.toUpperCase());
        books.forEach(System.out::println);
    }
}
