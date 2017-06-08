package com.example.lzw.myapp.Provide.services.impl;

import android.database.Cursor;

import com.example.lzw.myapp.Provide.services.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2017/06/08.
 */
public abstract class AObjectMetadata {
    protected abstract void populateYourColumnNames(List<String> colNameList);

    public String[] getColumnNames()
    {
        List<String> cols=new ArrayList<String>();
        populateYourColumnNames(cols);
        int count=cols.size();
        String[] columnNamesArray=new String[count];
        return cols.toArray(columnNamesArray);
    }


}
