package com.example.lzw.myapp.Provider.services.impl;

import android.util.Log;

import com.example.lzw.myapp.Provider.services.DatabaseContext;

/**
 * Created by Administrator on 2017/6/9.
 */

public class Database {
    private static final String tag="Database";
    public static void initialize()
    {
        DatabaseContext.initialize(DirectAccessBookDBHelper.m_self);
        Log.d(tag,"Database initialized");
    }

    public static DirectAccessBookDBHelper getDBHelper()
    {
        return DirectAccessBookDBHelper.m_self;
    }
}
