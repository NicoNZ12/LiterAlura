package com.nicolasnunez.LiterAlura.main;


import com.nicolasnunez.LiterAlura.dto.BookDTO;
import com.nicolasnunez.LiterAlura.dto.JsonDTO;

import com.nicolasnunez.LiterAlura.service.ConnectionAPI;
import com.nicolasnunez.LiterAlura.service.DataConvertion;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private ConnectionAPI cnx = new ConnectionAPI();
    private DataConvertion dataConvertion = new DataConvertion();
    private static final String API_URL = "https://gutendex.com/books";


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
                        System.out.println("Escriba el nombre del libro: ");
                        String bookName = sc.nextLine();
                        getBook(bookName);
                        break;
                    case 2:
                        System.out.println("listar libros...");
                        break;
                    case 3:
                        System.out.println("listar autores...");
                        break;
                    case 4:
                        System.out.println("listar autores vivos...");
                        break;
                    case 5:
                        System.out.println("listar libros por idioma...");
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

    private void getBook(String bookName){
        String json = cnx.getData(API_URL+"/?search="+bookName.replace(" ", "+"));
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);
        Optional<BookDTO> books = results.bookResults().stream()
                .findFirst();

        if(books.isPresent()){
            System.out.println(books.get());
        }else{
            System.out.println("No se encontró el libro: " + bookName + "\n");
        }
    }
}
