package com.example.lzw.myapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by LZW on 2016/09/29.
 */
public class ClientFrag extends Fragment {
    private static final String TAG="MessengerClientFrag";
    static private ClientFrag mClientFrag=null;
    private Context appContext=null;

    Messenger mService=null;

    boolean mIsBound;


    public static ClientFrag getInstance()
    {
        if(mClientFrag==null)
        {
            mClientFrag=new ClientFrag();
            mClientFrag.setRetainInstance(true);
        }
        return mClientFrag;
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case MessengerService.MSG_SET_SIMPLE_VALUE:
                    updateStatus("Received from service: "+msg.arg1);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    final Messenger mMessenger=new Messenger(new IncomingHandler());

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService=new Messenger(iBinder);
            updateStatus("Attached.");

            try
            {
                Message msg=Message.obtain(null,MessengerService.MSG_REGISTER_CLIENT);
                msg.replyTo=mMessenger;
                mService.send(msg);
            }
            catch (RemoteException e)
            {
                Log.e(TAG,"Could not establish a connection to the service:"+e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
                mService=null;
            updateStatus("Disconnected.");
        }
    };

    public void doBindService()
    {
        appContext=getActivity().getApplicationContext();
        if(mIsBound=appContext.bindService(new Intent("com.example.lzw.myapp.MessengerService"),mConnection,Context.BIND_AUTO_CREATE))
        {
            updateStatus("Bound to service.");
        }else
        {
            updateStatus("Bind attempt failed.");
        }
    }

    public void doUnbindService(){
        if(mIsBound)
        {
            if(mService!=null)
            {
                try{
                    Message msg=Message.obtain(null,MessengerService.MSG_UNREGISTER_CLIENT);
                    msg.replyTo=mMessenger;
                    mService.send(msg);
                }
                catch (RemoteException e)
                {

                }
            }
            appContext.unbindService(mConnection);
            mIsBound=false;
            updateStatus("Unbound.");
        }
    }

    public void doSendSimple(){
        try
        {
            Message msg=Message.obtain(null,MessengerService.MSG_SET_SIMPLE_VALUE,this.hashCode(),0);
            mService.send(msg);
            updateStatus("Sending simple message.");
        }
        catch (RemoteException e)
        {
            Log.e(TAG,"Could not send a simple message to the service:"+e);
        }
    }

    public void doSendComplex(){
        try
        {
            Message msg=Message.obtain(null,MessengerService.MSG_SET_COMPLEX_VALUE);
            Bundle mBundle=new Bundle();
            mBundle.putString("stringArg","This is a string to pass");
            mBundle.putDouble("myDouble",1138L);
            mBundle.putInt("myInt",42);
            msg.setData(mBundle);
            mService.send(msg);
            updateStatus("Sending complex message.");
        }
        catch (RemoteException e)
        {
            Log.e(TAG,"Could not send a complex message to the service:"+e);
        }
    }

    private void updateStatus(String status)
    {
        ISampleServiceClient uiContext=(ISampleServiceClient)getActivity();
        if(uiContext!=null)
        {
            uiContext.updateStatus(status);
        }
    }
}
