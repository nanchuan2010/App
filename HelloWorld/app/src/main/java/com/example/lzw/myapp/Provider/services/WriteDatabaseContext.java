package com.example.lzw.myapp.Provider.services;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/6/8.
 */

public class WriteDatabaseContext extends DatabaseContext {
    public WriteDatabaseContext(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected void internalBeginTransaction() {
        db.beginTransaction();
    }

    @Override
    protected void internalSetTransactionSuccessful() {
        db.setTransactionSuccessful();
    }

    @Override
    protected void internalEndTransaction() {
        db.endTransaction();
    }
}
