package com.example.lzw.myapp.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LZW on 2017/05/31.
 */
public class MultipleReceivers extends BroadcastReceiver {
    private static final String tag="MultipleReceivers";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(tag,"intent="+intent);
        String message=intent.getStringExtra("message");
        Log.d(tag,message);
        Toast.makeText(context,tag+":"+message,Toast.LENGTH_SHORT).show();
    }
}
