package com.nicolasnunez.LiterAlura.main;


import com.nicolasnunez.LiterAlura.dto.BookDTO;
import com.nicolasnunez.LiterAlura.service.ConnectionAPI;
import com.nicolasnunez.LiterAlura.service.DataConvertion;

public class Main {
    private ConnectionAPI cnx = new ConnectionAPI();
    private DataConvertion dataConvertion = new DataConvertion();

    public BookDTO convertJson(){
        String json = cnx.getData("https://gutendex.com/books/324/");
        BookDTO book = dataConvertion.convertData(json, BookDTO.class);
        System.out.println(book);
        return book;
    }
}
