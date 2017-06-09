package com.example.lzw.myapp.Provider.services.impl;

import android.database.sqlite.SQLiteDatabase;

import com.example.lzw.myapp.Provider.services.DatabaseContext;

/**
 * Created by LZW on 2017/06/08.
 */
public abstract class ASQLitePS {
    protected SQLiteDatabase getDb()
    {
        return DatabaseContext.getDb();
    }

    protected SQLiteDatabase getWriteDb()
    {
        return getDb();
    }

    protected SQLiteDatabase getReadDb()
    {
        return getDb();
    }


}
