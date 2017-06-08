package com.example.lzw.myapp.Provide.services.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lzw.myapp.MyApplication;
import com.example.lzw.myapp.Provide.services.DatabaseContext;
import com.example.lzw.myapp.Provide.services.ReadDatabaseContext;
import com.example.lzw.myapp.Provide.services.WriteDatabaseContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */

public class DirectAccessBookDBHelper extends SQLiteOpenHelper implements DatabaseContext.IFactory {
    public static DirectAccessBookDBHelper m_self=new DirectAccessBookDBHelper(MyApplication.m_appContext);

    private static final String DATABASE_NAME="bookSQLite.db";
    private static final String CREATE_DATABASE_FILENAME="create-book-db.sql";

    public static final int DATABASE_VERSION=1;

    private static final String TAG="DirectAccessBookDBHelper";

    DirectAccessBookDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            loadSQLFrom(this.CREATE_DATABASE_FILENAME,db);
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

    private void loadSQLFrom(String assetFilename,SQLiteDatabase db)
    {
        List<String> statements=getDDLStatementsFrom(assetFilename);
        for (String stmt :
                statements) {
            Log.d(TAG, "Executing Statement:" + stmt);
            db.execSQL(stmt);
        }
    }

    private List<String> getDDLStatementsFrom(String assetFilename)
    {
        ArrayList<String> l=new ArrayList<String>();
        String s=getStringFromAssetFile(assetFilename);
        for (String stmt :
                s.split(";")) {
            if (isValid(stmt))
            {
                l.add(stmt);
            }
        }
        return l;
    }

    private boolean isValid(String s)
    {
        return true;
    }

    private String getStringFromAssetFile(String filename)
    {
        Context ctx=MyApplication.m_appContext;
        if(ctx==null)
        {
            throw new RuntimeException("Sorry your app context is null");
        }

        try{
            AssetManager am=ctx.getAssets();
            InputStream is=am.open(filename);
            String s=convertStreamToString(is);
            is.close();
            return s;
        }
        catch (IOException x)
        {
            throw new RuntimeException("Sorry not able to read filename: "+filename,x);
        }
    }

    private String convertStreamToString(InputStream is)
        throws IOException
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int i=is.read();
        while(i!=-1)
        {
            baos.write(i);
            i=is.read();
        }
        return baos.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ReadDatabaseContext createReadableDatabase()
    {
        return new ReadDatabaseContext(this.getReadableDatabase());
    }

    public WriteDatabaseContext createWritableDatabase()
    {
        return new WriteDatabaseContext(this.getWritableDatabase());
    }
}
