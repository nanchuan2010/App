package com.example.lzw.myapp.Provider;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/8.
 */

public class JsonUtil {
    public void storeJSON(Context context,Object anyObject)
    {
        Gson gson=new Gson();
        String jsonString=gson.toJson(anyObject);

        String fileName="somefilename.xml";
        int mode=Context.MODE_PRIVATE;
        SharedPreferences sp=context.getSharedPreferences(fileName,mode);

        SharedPreferences.Editor spe=sp.edit();
        spe.putString("json",jsonString);
        spe.commit();
    }

    public Object retrieveJSON(Context context,String filename,Class classRef)
    {
        int mode=Context.MODE_PRIVATE;
        SharedPreferences sp=context.getSharedPreferences(filename,mode);
        String jsonString =sp.getString("json",null);
        if(jsonString==null)
        {
            throw new RuntimeException("Not able to read the preference");
        }

        Gson gson=new Gson();
        return gson.fromJson(jsonString,classRef);
    }


    private Object readFromInternalFile(Context appContext,String filename,Class classRef)
            throws Exception
    {
        FileInputStream fis=null;
        try
        {

            fis=appContext.openFileInput(filename);
            String jsonString="";

            Gson gson=new Gson();
            return gson.fromJson(jsonString,classRef);
        }
        finally {

        }
    }

    private void saveToInternalFile(Context appContext,String filename,Object anyObject)
            throws IOException
    {
        Gson gson=new Gson();
        String jsonString=gson.toJson(anyObject);

        FileOutputStream fos=null;
        try {
            fos=appContext.openFileOutput(filename,Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
        }
        finally {

        }
    }

}
