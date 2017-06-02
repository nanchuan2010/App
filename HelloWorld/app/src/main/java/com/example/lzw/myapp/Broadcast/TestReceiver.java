package com.example.lzw.myapp.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LZW on 2017/05/31.
 */
public class TestReceiver extends BroadcastReceiver {
    private static final String tag="TestReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TestReceiver","intent="+intent);
        String message=intent.getStringExtra("message");
        Log.d(tag,message);
    }
}
