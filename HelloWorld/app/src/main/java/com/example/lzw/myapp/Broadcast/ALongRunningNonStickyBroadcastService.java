package com.example.lzw.myapp.Broadcast;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

/**
 * Created by LZW on 2017/06/01.
 */
public abstract class ALongRunningNonStickyBroadcastService extends IntentService {
    public static String tag = "ALongRunningBroadcastService";

    protected abstract void handleBroadcastIntent(Intent broadcastIntent);

    public ALongRunningNonStickyBroadcastService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LightedGreenRoom.setup(this.getApplicationContext());
        LightedGreenRoom.s_registerClient();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        LightedGreenRoom.s_enter();
        return Service.START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Intent broadcastIntent = intent.getParcelableExtra("original_intent");
            handleBroadcastIntent(broadcastIntent);
        } finally {
            LightedGreenRoom.s_leave();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LightedGreenRoom.s_unRegisterClient();
    }
}
