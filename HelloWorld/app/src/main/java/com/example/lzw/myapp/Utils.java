package com.example.lzw.myapp;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by LZW on 2016/09/09.
 */
public class Utils {
    public static long getThreadId()
    {
        Thread t=Thread.currentThread();
        return t.getId();
    }

    public static String getThreadSignature()
    {
        Thread t=Thread.currentThread();
        long l=t.getId();
        String name=t.getName();
        long p=t.getPriority();
        String gname=t.getThreadGroup().getName();
        return (name+":(id)"+l+":(priority)"+p+":(group)"+gname);
    }

    public static void logThreadSignature()
    {
        Log.d("ThreadUtils",getThreadSignature());
    }
 public static void logThreadSignature(String tag)
 {
     Log.d(tag,getThreadSignature());
 }
    public static void sleepForInSecs(int secs)
    {
        try
        {
            Thread.sleep(secs*1000);
        }
        catch (InterruptedException x)
        {
            throw new RuntimeException("interrupted",x);
        }
    }

    public static Bundle getStringAsABundle(String message)
    {
        Bundle b=new Bundle();
        b.putString("message",message);
        return b;
    }

    public static String getStringFromABundle(Bundle b)
    {
        return b.getString("message");
    }

    public static Calendar getTimeAfterInSecs(int secs)
    {
        Calendar cal= Calendar.getInstance().getInstance();
        cal.add(Calendar.SECOND,secs);
        return cal;
    }
}
