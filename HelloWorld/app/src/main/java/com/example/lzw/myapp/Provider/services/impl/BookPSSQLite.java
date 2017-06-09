package com.example.lzw.myapp.Provider.services.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lzw.myapp.Provider.services.Book;
import com.example.lzw.myapp.Provider.services.IBookPS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public class BookPSSQLite extends ASQLitePS implements IBookPS{
    private static String tag="BookPSSQLite";


    @Override
    public int saveBook(Book book) {
        if(book.getId()==-1)
        {
            return (int)createBook(book);
        }
        updateBook(book);
        return book.getId();
    }

    private long createBook(Book book)
    {
        SQLiteDatabase db=getWriteDb();
        ContentValues bcv=this.getBookAsContentValuesForCreate(book);
        String nullColumnNameHack=null;
        long rowId=db.insertOrThrow(BookSQLiteMetaData.TABLE_NAME,nullColumnNameHack,bcv);
        return rowId;
    }

    private ContentValues getBookAsContentValuesForCreate(Book book)
    {
        ContentValues cv=new ContentValues();
        BookSQLiteMetaData.s_self.fillAllColumnValues(cv,book);
        return cv;
    }

    @Override
    public Book getBook(int bookid) {
        SQLiteDatabase db=getReadDb();
        String tname=BookSQLiteMetaData.TABLE_NAME;
        String[] colnames=BookSQLiteMetaData.s_self.getColumnNames();

        String selection=String.format("%s=%s",BookSQLiteMetaData.ID_COLNAME,bookid);
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderby=null;
        String limitClause=null;

        Cursor c=db.query(tname,colnames,selection,selectionArgs,groupBy,having,orderby,limitClause);

        try {
            if(c.isAfterLast())
            {
                Log.d(tag,"No rows for id"+ bookid);
                return null;
            }

            Book b=new Book();
            BookSQLiteMetaData.s_self.fillFields(c,b);
            return b;
        } finally {
            c.close();
        }
    }

    @Override
    public void updateBook(Book book) {
        if(book.getId()<0)
        {
            throw new SQLException("Book id is less than 0");
        }

        SQLiteDatabase db=getWriteDb();
        ContentValues bcv=this.getBookAsContentValuesForUpdate(book);
        String whereClause=String.format("%1=%2",BookSQLiteMetaData.ID_COLNAME,book.getId());

/*        String whereClause2="?=?";
        String[] whereArgs=new String[2];
        whereArgs[0]=BookSQLiteMetaData.ID_COLNAME;
        whereArgs[1]=Integer.toString(book.getId());*/

        int count=db.update(BookSQLiteMetaData.TABLE_NAME,bcv,whereClause,null);
        if(count==0)
        {
            throw new SQLException(String.format("Failed to update book for book id:%s",book.getId()));
        }
    }

    private ContentValues getBookAsContentValuesForUpdate(Book book) {
        ContentValues cv=new ContentValues();
        BookSQLiteMetaData.s_self.fillUpdatableColumnValues(cv,book);
        return cv;
    }

    @Override
    public void deleteBook(int bookid) {
        SQLiteDatabase db=getWriteDb();
        String tname=BookSQLiteMetaData.TABLE_NAME;
        String whereClause=String.format("%s=%s;",BookSQLiteMetaData.ID_COLNAME,bookid);
        String[] whereClauseargs=null;
        int i=db.delete(tname,whereClause,whereClauseargs);
        if(i!=1)
        {
            throw new RuntimeException("The number of deleted books is not 1 but:"+i);
        }
    }

    @Override
    public List<Book> getAllBooks() {
       SQLiteDatabase db=getReadDb();
        String tname=BookSQLiteMetaData.TABLE_NAME;
        String[] colnames=BookSQLiteMetaData.s_self.getColumnNames();

        String selection=null;
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderby=null;
        String limitClause=null;
        Cursor c=null;

        try {
            c=db.query(tname,colnames,selection,selectionArgs,groupBy,having,orderby,limitClause);
            List<Book> bookList=new ArrayList<Book>();
            for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                Log.d(tag,"There are books");
                Book b=new Book();
                BookSQLiteMetaData.s_self.fillFields(c,b);
                bookList.add(b);
            }
            return bookList;
        } finally {
            if(c!=null)
                c.close();
        }
    }
}
