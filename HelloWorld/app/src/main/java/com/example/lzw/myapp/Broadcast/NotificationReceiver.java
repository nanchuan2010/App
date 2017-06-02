package com.example.lzw.myapp.Broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/05/31.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private static final String tag="Notification Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(tag,"intent="+intent);
        String message=intent.getStringExtra("message");
        Log.d(tag,message);
        this.sendNotification(context,message);
    }

    private void sendNotification(Context ctx,String message)
    {
        String ns=Context.NOTIFICATION_SERVICE;
        NotificationManager nm=(NotificationManager)ctx.getSystemService(ns);
        int icon= R.drawable.robot;
        CharSequence tickerText="Hello";
        long when=System.currentTimeMillis();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        PendingIntent pi=PendingIntent.getActivity(ctx,0,intent,0);
        Notification notification=new Notification.Builder(ctx)
                .setContentTitle("title")
                .setContentText(tickerText)
                .setSmallIcon(icon)
                .setWhen(when)
                .setContentIntent(pi)
                .setContentInfo("Addtional Information:Content Info")
                .build();
        nm.notify(1,notification);
    }
}
