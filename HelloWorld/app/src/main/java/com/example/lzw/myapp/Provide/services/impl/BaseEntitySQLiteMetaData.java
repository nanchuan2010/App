package com.example.lzw.myapp.Provide.services.impl;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.lzw.myapp.Provide.services.BaseEntity;
import com.example.lzw.myapp.Provide.services.Book;

import java.util.Date;
import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public class BaseEntitySQLiteMetaData extends AObjectMetadata{
    static public final String OWNED_ACCOUNT_COLNAME="owned_account";
    static public final String CREATED_BY_COLUMN="created_by";
    static public final String CREATED_ON_COLUMN="created_on";
    static public final String LAST_UPDATED_ON="last_updated_on";
    static public final String LAST_UPDATED_BY="last_updated_by";
    static public final String ID_COLNAME="id";

    @Override
    protected void populateYourColumnNames(List<String> colNameList) {
        colNameList.add(CREATED_BY_COLUMN);
        colNameList.add(CREATED_ON_COLUMN);
        colNameList.add(ID_COLNAME);
        colNameList.add(LAST_UPDATED_BY);
        colNameList.add(LAST_UPDATED_ON);
    }

    public void fillAllColumnValues(ContentValues cv, Book book) {
        cv.put(CREATED_BY_COLUMN,book.getCreatedBy());
        cv.put(CREATED_ON_COLUMN,fromDate(book.getCreateOn()));
        this.fillMyCommonColumnValues(cv,book);
    }

    private void fillMyCommonColumnValues(ContentValues cv, Book book) {
        cv.put(LAST_UPDATED_BY,book.getLastUpdatedBy());
        cv.put(LAST_UPDATED_ON,fromDate(book.getLastUpdatedOn()));
    }

    private long fromDate(Date date)
    {
        return date.getTime();
    }

    public void fillUpdatableColumnValues(ContentValues cv, Book book) {
        this.fillMyCommonColumnValues(cv,book);
    }

    public void fillFields(Cursor c, BaseEntity be) {
        be.setId(c.getInt(c.getColumnIndex(ID_COLNAME)));
        be.setCreatedBy(c.getString(c.getColumnIndex(CREATED_BY_COLUMN)));
        be.setCreateOn(getDate(c.getColumnIndex(CREATED_ON_COLUMN)));
        be.setLastUpdatedBy(c.getString(c.getColumnIndex(LAST_UPDATED_BY)));
        be.setLastUpdatedOn(getDate(c.getColumnIndex(LAST_UPDATED_ON)));
    }

    private Date getDate(long datenumber) {
        return new Date(datenumber);
    }
}


