package com.example.lzw.myapp.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LZW on 2017/06/01.
 */
public abstract class ALongRunningReceiver extends BroadcastReceiver {
    private static final String tag = "ALongRunningReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(tag, "Receiver started");
        LightedGreenRoom.setup(context);
        startService(context, intent);

        Log.d(tag, "Receiver finished");
    }

    private void startService(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, getLRSClass());
        serviceIntent.putExtra("original_intent", intent);
        context.startService(serviceIntent);
    }


    public abstract Class getLRSClass();
}
