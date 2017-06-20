package com.example.lzw.myapp.AsyncTask.;

import android.app.Activity;

import com.example.lzw.myapp.AsyncTask.MyLongTaskWithRADO;

/**
 * Created by LZW on 2017/05/31.
 */
public class AsyncTesterRADO extends BaseTesterRADO {
    private final static String tag="AsyncTesterRADO";

    public AsyncTesterRADO(Activity act) {
        super(act, tag);
    }

    public void testFragmentProgressDialog()
    {
        logStatus();
        MyLongTaskWithRADO longTaskWithFragmentDialog3=new MyLongTaskWithRADO(this,this,"Task With Dialogs");
        longTaskWithFragmentDialog3.execute("String1","String2","String3");
    }
}
