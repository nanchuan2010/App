package com.example.lzw.myapp.AsyncTask;

import android.content.Context;

import com.example.lzw.myapp.BaseListener;
import com.example.lzw.myapp.IReportBack;

/**
 * Created by LZW on 2017/05/25.
 */
public class AsyncTester extends BaseListener {

    private static String tag = "AsyncTester1";

    AsyncTester(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void test1() {
        MyLongTask1 mlt = new MyLongTask1(this.mReportTo, this.mContext, "Task1");
        mlt.execute("String1", "String2", "String3");
    }

    public void test2() {
        MyLongTask2 mlt = new MyLongTask2(this.mReportTo, this.mContext, "Task2");
        mlt.execute("String1", "String2", "String3");
    }


    public void test3() {
        //依然是串行执行，并行执行用executeOnExecutor()
        MyLongTask1 mlt = new MyLongTask1(this.mReportTo, this.mContext, "Task1");
        mlt.execute("String1", "String2", "String3");

        MyLongTask1 mlt1 = new MyLongTask1(this.mReportTo, this.mContext, "Task2");
        mlt1.execute("String1", "String2", "String3");
    }
}
