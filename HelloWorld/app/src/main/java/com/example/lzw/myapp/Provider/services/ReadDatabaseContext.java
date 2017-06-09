package com.example.lzw.myapp.Provider.services;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/6/8.
 */

public class ReadDatabaseContext extends DatabaseContext {
    public ReadDatabaseContext(SQLiteDatabase db)
    {
        super(db);
    }
}
