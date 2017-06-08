package com.example.lzw.myapp.Provide.services;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DatabaseContext {
    public enum ReadWriteType{Read,Write;}

    protected SQLiteDatabase db=null;

    protected static  IFactory dbfactory=null;

    public DatabaseContext(SQLiteDatabase db)
    {
        this.db=db;
    }


    public static interface IFactory
    {
        ReadDatabaseContext createReadableDatabase();
        WriteDatabaseContext createWritableDatabase();
    }

    public static ReadDatabaseContext createReadableDatabaseContext()
    {
        return dbfactory.createReadableDatabase();
    }

    public static WriteDatabaseContext createWriteDatabaseContext()
    {
        return dbfactory.createWritableDatabase();
    }

    public static ThreadLocal<DatabaseContext> t1_DatabaseContext=new ThreadLocal<DatabaseContext>();

    public static void setWritableDatabaseContext()
    {
        DatabaseContext dc=createWriteDatabaseContext();
        t1_DatabaseContext.set(dc);
    }

    public static void setReadableDatabaseContext()
    {
        DatabaseContext dc=createReadableDatabaseContext();
        t1_DatabaseContext.set(dc);
    }

    public static DatabaseContext getCurrentDatabaseContext()
    {
        return (DatabaseContext)t1_DatabaseContext.get();
    }

    public static boolean isItAlreadyInsideATransaction()
    {
        DatabaseContext dc=getCurrentDatabaseContext();
        if(dc!=null)
            return true;
        return false;
    }

    public static SQLiteDatabase getDb()
    {
        return getCurrentDatabaseContext().db;
    }

    public static void reset()
    {
        t1_DatabaseContext.set(null);
    }

    public static void beginTransaction()
    {
        getCurrentDatabaseContext().internalBeginTransaction();
    }

    public static void setTransactionSuccessful()
    {
        getCurrentDatabaseContext().internalSetTransactionSuccessful();
    }

    public static void endTransaction()
    {
        getCurrentDatabaseContext().internalEndTransaction();
    }

    protected void internalBeginTransaction(){}

    protected void internalSetTransactionSuccessful(){}

    protected void internalEndTransaction(){}

    public static void initialize(DatabaseContext.IFactory factory)
    {
        DatabaseContext.dbfactory=factory;
    }
}
