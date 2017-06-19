package com.example.lzw.myapp.Handlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2016/09/09.
 */
public class ReportStatusHandler extends Handler {
    public static final String tag="TestHandler2";
    private HandlersDriverActivity parentActivity=null;

    public ReportStatusHandler(HandlersDriverActivity inParentActivity)
    {
        parentActivity=inParentActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        String pm= Utils.getStringFromABundle(msg.getData());

        Log.d(tag,pm);
        this.printMessage(pm);
    }

    private void printMessage(String msg)
    {
        parentActivity.appendText(msg);
    }
}
