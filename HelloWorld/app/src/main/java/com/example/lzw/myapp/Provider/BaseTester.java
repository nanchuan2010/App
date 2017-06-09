package com.example.lzw.myapp.Provider;

import android.content.Context;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/9.
 */

public class BaseTester {
    protected IReportBack mReportTo;
    protected Context mContext;
    private String tag;

    public BaseTester(Context ctx,IReportBack target,String debugtag)
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
