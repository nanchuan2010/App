package com.example.lzw.myapp.AsyncTask;

import com.example.lzw.myapp.AsyncTask.IWorkerObject;

/**
 * Created by LZW on 2017/05/31.
 */
public interface IWorkerObjectClient {
    public void done(IWorkerObject wobj, int workerObjectPassthroughIdentifier);
}
