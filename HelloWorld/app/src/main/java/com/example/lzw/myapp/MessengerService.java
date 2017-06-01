package com.example.lzw.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by LZW on 2016/09/27.
 */
public class MessengerService extends Service {
    NotificationManager mNM;
    ArrayList<Messenger> mClient=new ArrayList<Messenger>();
    int mValue=0;
    public static final int MSG_REGISTER_CLIENT=1;
    public static final int MSG_UNREGISTER_CLIENT=2;
    public static final int MSG_SET_SIMPLE_VALUE=3;
    public static final int MSG_SET_COMPLEX_VALUE=4;
    public static final String TAG="MessengerService";

    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case MSG_REGISTER_CLIENT:
                    mClient.add(msg.replyTo);
                    Log.v(TAG,"Registering client");
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClient.remove(msg.replyTo);
                    Log.v(TAG,"Unregistering client");
                    break;
                case MSG_SET_SIMPLE_VALUE:
                    mValue=msg.arg1;
                    Log.v(TAG,"Receiving arg1:"+mValue);
                    showNotification("Received arg1:"+mValue);
                    for (int i = mClient.size()-1; i >=0 ; i--) {
                        try
                        {
                            mClient.get(i).send(Message.obtain(null,MSG_SET_SIMPLE_VALUE,mValue,0));
                        }
                        catch(RemoteException e)
                        {
                            mClient.remove(i);
                        }
                    }
                    break;
                case MSG_SET_COMPLEX_VALUE:
                    Bundle mBundle=msg.getData();
                    Log.v(TAG,"Receiving bundle: ");
                    if(mBundle!=null)
                    {
                        showNotification("Got complex msg: myDouble="+mBundle.getDouble("myDouble"));
                        for (String key:mBundle.keySet())
                        {
                            Log.v(TAG," "+key);
                        }
                    }
                    break;
                default:
                    Log.v(TAG,"Got some other message: "+msg.what);
                    super.handleMessage(msg);
            }

        }

    }


    final Messenger mMessenger=new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        mNM=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Log.v(TAG,"Service is starting");
        showNotification(getText(R.string.remote_service_started));
    }

    @Override
    public void onDestroy() {
        mNM.cancel(R.string.remote_service_started);
        Toast.makeText(this,R.string.remote_service_stopped,Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private void showNotification(CharSequence text)
    {
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("MessengerService")
                .setContentText(text)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker(text)
                .setOngoing(true)
                .build();
        mNM.notify(R.string.remote_service_started,notification);
    }
}
