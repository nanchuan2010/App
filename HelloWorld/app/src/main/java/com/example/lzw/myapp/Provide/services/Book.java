package com.example.lzw.myapp.Provide.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public class Book extends BaseEntity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private String name;
    private String author;
    private String isbn;

    public Book(String ownedAccount, String createdBy, Date createdOn,String lastUpdatedBy,
                Date lastUpdatedOn,String name,String author,String isbn)
    {
        super(ownedAccount,createdBy,createdOn,lastUpdatedBy,lastUpdatedOn,-1);
        this.name=name;
        this.author=author;
        this.isbn=isbn;
    }

    public Book(){}

    public static Book createAMockBook()
    {
        String ownedAccount="Account1";
        String createdBy="satya";
        Date createdOn= Calendar.getInstance().getTime();
        String lastUpdatedBy="satya";
        Date lastUpdatedOn=Calendar.getInstance().getTime();

        List<Book> books=Services.PersistenceServices.bookps.getAllBooks();
        int i=books.size();
        String name=String.format("Book %s",i);
        String author="satya";
        String isbn="isbn-12344-"+i;
        return new Book(ownedAccount,createdBy,createdOn,lastUpdatedBy,lastUpdatedOn,name,author,isbn);
    }
}
