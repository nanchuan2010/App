package com.example.lzw.myapp.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Utils;

import java.util.Calendar;

/**
 * Created by LZW on 2017/06/01.
 */
public class SendRepeatingAlarmTester extends SendAlarmOnceTester {
    private static String tag="SendRepeatingAlarmTester";

    SendRepeatingAlarmTester(Context ctx, IReportBack target)
    {
        super(ctx,target);
    }

    public void sendRepeatingAlarm()
    {
        Calendar cal= Utils.getTimeAfterInSecs(30);


        Intent intent=new Intent(mContext,TestReceiver.class);
        intent.putExtra("message","Repeating Alarm");

        PendingIntent pendingIntent=PendingIntent.getBroadcast(mContext,2,intent,0);

        AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),5*1000,pendingIntent);
    }
}
