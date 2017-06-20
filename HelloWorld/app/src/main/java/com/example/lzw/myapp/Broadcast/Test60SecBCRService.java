package com.example.lzw.myapp.Broadcast;

import android.content.Intent;
import android.util.Log;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/06/01.
 */
public class Test60SecBCRService extends ALongRunningNonStickyBroadcastService {
    public static String tag = "Test60SecBCRService";

    public Test60SecBCRService() {
        super("com.android.service.Test60SecBCRService");
    }

    @Override
    protected void handleBroadcastIntent(Intent broadcastIntent) {
        Utils.logThreadSignature(tag);
        Log.d(tag, "Sleeping for 60 secs");
        Utils.sleepForInSecs(60);
        String message = broadcastIntent.getStringExtra("message");
        Log.d(tag, "Job completed");
        Log.d(tag, message);
    }

}
