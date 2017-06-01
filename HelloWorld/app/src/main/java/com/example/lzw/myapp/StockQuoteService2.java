package com.example.lzw.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;


/**
 * Created by LZW on 2016/09/18.
 */
public class StockQuoteService2 extends Service {
    private NotificationManager notificationMgr;

    public class StockQuoteServiceImpl extends IStockQuoteService2.Stub
    {
        @Override
        public String getQuote(String ticker, Person requester) throws RemoteException {
            return "Hello "+requester.getName()+"!Quote for "+ticker+" is 20.00";
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationMgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        displayNotificationMessage("onCreate() called in StockQuoteService2");
    }

    @Override
    public void onDestroy() {
        displayNotificationMessage("onDestroy() called in StockQuoteService2");
        notificationMgr.cancelAll();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        displayNotificationMessage("onBind() called in StockQuoteService2");
        return new StockQuoteServiceImpl();
    }

    private void displayNotificationMessage(String message) {
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,new Intent(this,ServiceActivity.class),0);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("StockQuoteService2")
                .setContentText(message)
                .setSmallIcon(R.drawable.emo_im_winking)
                .setTicker(message)
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .build();

        notificationMgr.notify(R.id.app_notification_id,notification);
    }
}
