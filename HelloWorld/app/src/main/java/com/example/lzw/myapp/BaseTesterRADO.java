package com.example.lzw.myapp;

import android.app.Activity;
import android.util.Log;

/**
 * Created by LZW on 2017/05/31.
 */
public class BaseTesterRADO extends RetainedADO implements IReportBack {
    public BaseTesterRADO(Activity act,String tag)
    {
        super(act,tag);
        if(!(act instanceof IReportBack))
        {
            throw new RuntimeException("Sorry,the activity should support IReportBack interface");
        }
    }

    public void reportBack(String tag,String message)
    {
        if(!isActivityReady())
        {
            Log.d(tag,"Sorry activity is not ready during reportback");
            return;
        }

        getReportBack().reportBack(tag,message);
    }

    private IReportBack getReportBack()
    {
        return (IReportBack)getActivity();
    }

    public void reportTransient(String tag,String message)
    {
        if(!isActivityReady())
        {
            Log.d(tag,"Sorry activity is not ready during reportback");
            return;
        }

        getReportBack().reportBack(tag,message);
    }
}
