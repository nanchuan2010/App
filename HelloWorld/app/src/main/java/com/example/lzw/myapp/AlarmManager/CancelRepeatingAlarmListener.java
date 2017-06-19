package com.example.lzw.myapp.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by LZW on 2017/06/01.
 */
public class CancelRepeatingAlarmListener extends SendRepeatingAlarmListener {
    private static String tag="CancelRepeatingAlarmListener";
    CancelRepeatingAlarmListener(Context ctx, IReportBack target)
    {
        super(ctx,target);
    }

    public void cancelRepeatingAlarm()
    {
        Intent intent=new Intent(this.mContext,TestReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(mContext,2,intent,0);
        AlarmManager am=(AlarmManager)this.mContext.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
        this.mReportTo.reportBack(tag,"You shouldn't see alarms again");
    }
}
