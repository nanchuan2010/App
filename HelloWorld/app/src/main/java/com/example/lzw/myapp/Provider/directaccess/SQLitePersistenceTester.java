package com.example.lzw.myapp.Provider.directaccess;

import android.content.Context;

import com.example.lzw.myapp.BaseListener;
import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Provider.services.Book;
import com.example.lzw.myapp.Provider.services.IBookPS;
import com.example.lzw.myapp.Provider.services.Services;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class SQLitePersistenceTester extends BaseListener {

    private static String tag="SQLitePersistenceTester";

    private IBookPS bookPersistenceService= Services.PersistenceServices.bookps;

    public SQLitePersistenceTester(Context ctx, IReportBack target) {
        super(ctx, target, tag);
    }

    public void addBook()
    {
        Book book= Book.createAMockBook();
        int bookid=bookPersistenceService.saveBook(book);
        reportString(String.format("Inserted a book %s whose generated id now is %s",book.getName(),bookid));
    }

    public void removeBook()
    {
        List<Book> bookList=bookPersistenceService.getAllBooks();
        if(bookList.size()<=0)
        {
            reportString("There are no books that can be deleted");
            return;
        }

        reportString(String.format("There are %s books.First one will be deleted",bookList.size()));

        Book b=bookList.get(0);
        bookPersistenceService.deleteBook(b.getId());
        reportString(String.format("Book with id:%s successfully deleted",b.getId()));
    }

    public void showBooks()
    {
        List<Book> bookList=bookPersistenceService.getAllBooks();
        reportString(String.format("Number of books:%s",bookList.size()));
        for (Book b :
                bookList) {
            reportString(String.format("id:%s name:%s author:%s isbn:%s", b.getId(), b.getName(), b.getAuthor(), b.getIsbn()));
        }
    }

    private int getCount()
    {
        List<Book> bookList=bookPersistenceService.getAllBooks();
        return bookList.size();
    }

}
