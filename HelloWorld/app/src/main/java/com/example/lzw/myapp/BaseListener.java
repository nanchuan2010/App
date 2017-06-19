package com.example.lzw.myapp;

import android.content.Context;

/**
 * Created by LZW on 2017/05/25.
 */
public class BaseListener {
    protected IReportBack mReportTo;
    protected Context mContext;

    public BaseListener(Context ctx, IReportBack target)
    {
        mReportTo=target;
        mContext=ctx;
    }
}
