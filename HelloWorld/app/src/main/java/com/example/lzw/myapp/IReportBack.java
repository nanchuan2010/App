package com.example.lzw.myapp;

/**
 * Created by LZW on 2017/05/25.
 */
public interface IReportBack {
    public void reportBack(String tag,String message);
    public void reportTransient(String tag,String message);
}
