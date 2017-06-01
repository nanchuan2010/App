package com.example.lzw.myapp;

import android.content.Context;

/**
 * Created by LZW on 2017/05/25.
 */
public class AsyncTester extends BaseTester{

    private static String tag="AsyncTester1";
    AsyncTester(Context ctx,IReportBack target)
    {
        super(ctx,target);
    }



    void respondToMenuItem()
    {
        IReportBack reportBackObject=this.mReportTo;
        Context ctx=this.mContext;
        String tag="Task1";

        MyLongTask mlt=new MyLongTask(reportBackObject,ctx,tag);
        mlt.execute("String1","String2","String3");
    }
}
