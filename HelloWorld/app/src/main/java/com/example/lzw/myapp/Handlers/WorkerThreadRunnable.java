package com.example.lzw.myapp.Handlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2016/09/09.
 */
public class WorkerThreadRunnable implements Runnable {
    Handler mainThreadHandler=null;
    int count=0;
    public WorkerThreadRunnable(Handler handler)
    {
        mainThreadHandler=handler;
    }

    public static String tag="TestRunnable";
    public void run()
    {
        Log.d(tag,"start execution");
        informStart();
        for (int i=0;i<=10;i++)
        {
            Utils.sleepForInSecs(1);
            informMiddle(i);
        }

        informFinish();
    }

    public void informStart()
    {
        Message msg=this.mainThreadHandler.obtainMessage();
        msg.setData(Utils.getStringAsABundle("starting run"));
        this.mainThreadHandler.sendMessage(msg);
    }

    public void informMiddle(int count)
    {
        Message msg=this.mainThreadHandler.obtainMessage();
        msg.setData(Utils.getStringAsABundle("done:"+count));
        this.mainThreadHandler.sendMessage(msg);
    }

    public void informFinish()
    {
        Message msg=this.mainThreadHandler.obtainMessage();
        msg.setData(Utils.getStringAsABundle("Finishing run"));
        this.mainThreadHandler.sendMessage(msg);
    }
}
