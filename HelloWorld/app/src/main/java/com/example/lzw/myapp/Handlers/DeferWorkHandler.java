package com.example.lzw.myapp.Handlers;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by LZW on 2016/09/09.
 */
public class DeferWorkHandler extends Handler {
    public static final String tag="TestHandler1";
    private int count=0;

    private HandlersDriverActivity parentActivity=null;

    public DeferWorkHandler(HandlersDriverActivity inParentActivity)
    {
        parentActivity=inParentActivity;
    }


    @Override
    public void handleMessage(Message msg)
    {
        String pm=new String("message called:"+count+":"+msg.getData().getString("message"));
        Log.d(tag,pm);
        this.printMessage(pm);

        if(count>5)
            return;

        count++;
        sendTestMessage(1);
    }

    public void doDeferredWork()
    {
        count=0;
        sendTestMessage(1);
    }

    public void sendTestMessage(long interval){
        Message msg=this.obtainMessage();
        prepareMessage(msg);
        this.sendMessageDelayed(msg,interval*1000);
    }

    public void prepareMessage(Message msg){
        Bundle bundle=new Bundle();
        bundle.putString("message","Hello World");
        msg.setData(bundle);
        return;
    }

    private void printMessage(String xyz)
    {
        parentActivity.appendText(xyz);
    }
}
