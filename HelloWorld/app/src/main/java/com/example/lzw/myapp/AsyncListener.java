package com.example.lzw.myapp;

import android.content.Context;

/**
 * Created by LZW on 2017/05/25.
 */
public class AsyncListener extends BaseListener {

    private static String tag="AsyncTester1";
    AsyncListener(Context ctx, IReportBack target)
    {
        super(ctx,target);
    }

    public void test1()
    {
        MyLongTask mlt=new MyLongTask(this.mReportTo,this.mContext,"Task1");
        mlt.execute("String1","String2","String3");
    }

    public void test2()
    {
        MyLongTask1 mlt=new MyLongTask1(this.mReportTo,this.mContext,"Task1");
        mlt.execute("String1","String2","String3");
    }


    public void test3()
    {
        MyLongTask mlt=new MyLongTask(this.mReportTo,this.mContext,"Task1");
        mlt.execute("String1","String2","String3");

        MyLongTask mlt1=new MyLongTask(this.mReportTo,this.mContext,"Task2");
        mlt1.execute("String1","String2","String3");
    }
}
