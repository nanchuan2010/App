package com.example.lzw.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;


import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;

/**
 * Created by LZW on 2016/09/13.
 */
public class TestMultipartPost extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try{
            Ion.with(this,"http://www.androidbook.com/akc/update/PublicUploadTest")
                    .setMultipartParameter("field1","This is field number 1")
                    .setMultipartParameter("field2","Field 2 is shorter")
                    .setMultipartFile("datafile",new File(Environment.getExternalStorageDirectory()+"/testfile.txt"))
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String s) {
                            System.out.println(s);
                        }
                    });
        }
        catch (Exception e)
        {
            System.out.println("Got exception: "+e);
        }
    }
}
