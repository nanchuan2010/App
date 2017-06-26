package com.example.lzw.myapp;

import android.content.Context;

/**
 * Created by LZW on 2017/05/25.
 */
public class BaseListener {
    protected IReportBack mReportTo;
    protected Context mContext;
    protected String tag;

    public BaseListener(Context ctx, IReportBack target)
    {
        mReportTo=target;
        mContext=ctx;
    }

    public BaseListener(Context ctx, IReportBack target,String debugtag)
    {
        mReportTo=target;
        mContext=ctx;
        tag=debugtag;
    }

    protected void report(int stringid)
    {
        this.mReportTo.reportBack(tag,this.mContext.getString(stringid));
    }

    protected void reportString(String s)
    {
        this.mReportTo.reportBack(tag,s);
    }

    protected void reportString(String s,int stringid)
    {
        this.mReportTo.reportBack(tag,s);
        report(stringid);
    }
}
