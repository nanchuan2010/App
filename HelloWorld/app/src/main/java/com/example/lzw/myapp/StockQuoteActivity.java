package com.example.lzw.myapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class StockQuoteActivity extends Activity {

    private static final String TAG="StockQuoteClient";
    private IStockQuoteService2 stockService=null;
    private ToggleButton bindBtn;
    private Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_quote);

        bindBtn=(ToggleButton)findViewById(R.id.bindBtn);
        callBtn=(Button)findViewById(R.id.callBtn);
    }


    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bindBtn:
                if(((ToggleButton)view).isChecked())
                {
                    bindService(new Intent(IStockQuoteService2.class.getName()),serConn, Context.BIND_AUTO_CREATE);
                }
                else
                {
                    unbindService(serConn);
                    callBtn.setEnabled(false);
                }
                break;
            case R.id.callBtn:
                callService();
        }
    }

    private void callService()
    {
        try {
            Person person=new Person();
            person.setAge(47);
            person.setName("Dave");
            String response =stockService.getQuote("ANDROID",person);
            Toast.makeText(StockQuoteActivity.this,"Value from service is "+response,Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            Log.e("StockQuoteActivity",e.getMessage(),e);
        }
    }

    private ServiceConnection serConn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(TAG,"onServiceConnected() called");
            stockService=IStockQuoteService2.Stub.asInterface(iBinder);
            bindBtn.setChecked(true);
            callBtn.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(TAG,"onServiceDisconnected() called");
            bindBtn.setChecked(false);
            callBtn.setEnabled(false);
            stockService=null;
        }
    };

    protected void onDestroy()
    {
        Log.v(TAG,"onDestroy() called");
        if(callBtn.isEnabled())
        {
            unbindService(serConn);
        }
        super.onDestroy();
    }
}
