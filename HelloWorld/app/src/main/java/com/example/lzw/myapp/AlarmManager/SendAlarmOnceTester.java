package com.example.lzw.myapp.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.lzw.myapp.BaseTester;
import com.example.lzw.myapp.BaseTesterFragment;
import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Utils;

import java.util.Calendar;

/**
 * Created by LZW on 2017/06/01.
 */
public class SendAlarmOnceTester extends BaseTester {
    private static String tag="SendAlarmOnceTester";

    SendAlarmOnceTester(Context ctx, IReportBack target)
    {
        super(ctx,target);
    }

    public void sendAlarmOnce()
    {
        Calendar cal= Utils.getTimeAfterInSecs(30);


        Intent intent=new Intent(mContext,TestReceiver.class);
        intent.putExtra("message","Single Shot Alarm");

        PendingIntent pendingIntent=PendingIntent.getBroadcast(mContext,1,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);

        am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);

    }
}
