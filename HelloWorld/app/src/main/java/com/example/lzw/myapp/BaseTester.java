package com.example.lzw.myapp;

import android.content.Context;

/**
 * Created by LZW on 2017/05/25.
 */
public class BaseTester {
    protected IReportBack mReportTo;
    protected Context mContext;

    public BaseTester(Context ctx,IReportBack target)
    {
        mReportTo=target;
        mContext=ctx;
    }
}
