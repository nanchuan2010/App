package com.example.lzw.myapp.Provide.services.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.lzw.myapp.Provide.services.BaseEntity;
import com.example.lzw.myapp.Provide.services.Book;

import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public class BookSQLiteMetaData extends BaseEntitySQLiteMetaData {
    static public final String TABLE_NAME="t_books";
    static public final String NAME="name";
    static public final String AUTHOR="author";
    static public final String ISBN="isbn";

    @Override
    protected void populateYourColumnNames(List<String> colNameList) {
        super.populateYourColumnNames(colNameList);
        colNameList.add(NAME);
        colNameList.add(AUTHOR);
        colNameList.add(ISBN);
    }

    public static BookSQLiteMetaData s_self=new BookSQLiteMetaData();

    @Override
    public void fillAllColumnValues(ContentValues cv, Book book)
    {
        super.fillAllColumnValues(cv,book);
        fillMyColumnValues(cv,book);
    }

    private void fillMyColumnValues(ContentValues cv, Book book) {
        cv.put(BookSQLiteMetaData.NAME,book.getName());
        cv.put(BookSQLiteMetaData.ISBN,book.getIsbn());
        cv.put(BookSQLiteMetaData.AUTHOR,book.getAuthor());
    }

    public void fillUpdatableColumnValues(ContentValues cv, Book book) {
        super.fillUpdatableColumnValues(cv,book);
        fillMyColumnValues(cv,book);
    }

    public void fillFields(Cursor c, BaseEntity be) {
        super.fillFields(c,be);
        Book b=(Book)be;
        b.setName(c.getString(c.getColumnIndex(NAME)));
        b.setAuthor(c.getString(c.getColumnIndex(AUTHOR)));
        b.setIsbn(c.getString(c.getColumnIndex(ISBN)));
    }
}
