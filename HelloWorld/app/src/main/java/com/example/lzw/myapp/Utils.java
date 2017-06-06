package com.example.lzw.myapp;

import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static Date getDate(String dateString) throws ParseException
    {
        DateFormat format=getDateFormat();
        Date date=format.parse(dateString);
        return date;
    }

    public static DateFormat getDateFormat()
    {
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        return format;
    }

    public static boolean validateDate(String dateString)
    {
        try{
            SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
            format.setLenient(false);
            Date date=format.parse(dateString);
            return true;
        }
        catch (ParseException x)
        {
            return false;
        }
    }

    public static long howfarInDaysThisYear(Date date)
    {
        Calendar bdayCal=Calendar.getInstance();
        bdayCal.setTime(date);

        int bday=bdayCal.get(Calendar.DAY_OF_MONTH);
        int bmonth=bdayCal.get(Calendar.MONTH);

        Calendar todayCalendar=Calendar.getInstance();
        Calendar bdaycalThisYear=Calendar.getInstance();
        bdaycalThisYear.set(Calendar.DAY_OF_MONTH,bday);
        bdaycalThisYear.set(Calendar.MONTH,bmonth);
        bdaycalThisYear.set(Calendar.YEAR,todayCalendar.get(Calendar.YEAR));

        long today_ms=todayCalendar.getTimeInMillis();
        long bday_ms=bdaycalThisYear.getTimeInMillis();

        return (bday_ms-today_ms)/(1000*60*60*24);
    }
}
