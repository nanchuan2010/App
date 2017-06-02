package com.example.lzw.myapp;

import android.app.Activity;

/**
 * Created by LZW on 2017/05/31.
 */
public interface IWorkerObject {
    public void registerClient(IWorkerObjectClient woc,int workerObjectPassbackIdentifier);
    public void onStart(Activity act);
    public void releaseResources();
}
