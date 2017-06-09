package com.example.lzw.myapp.Provider.services;

import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public interface IBookPS {
    public int saveBook(Book book);
    public Book getBook(int bookid);
    public void updateBook(Book book);
    public void deleteBook(int bookid);
    public List<Book> getAllBooks();
}
