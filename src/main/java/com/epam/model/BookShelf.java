package com.epam.model;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private List<Book> bookShelf;

    public BookShelf(){
        bookShelf = new ArrayList<Book>();
        bookShelf.add(new Book(1,"Thinking in Java","Bruse Eckel","short summary"));
        bookShelf.add(new Book(2,"Methods of Programming","Blinov","short summary"));
    }

    public void addBook(Book book){
        bookShelf.add(book);
    }
}
