package com.example.lzw.myapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by LZW on 2016-08-03.
 */
public class MyApplication extends Application
{
    public static volatile Context s_appContext=null;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.s_appContext=this.getApplicationContext();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
