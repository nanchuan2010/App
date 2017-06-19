package com.example.lzw.myapp.Services;

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

import com.example.lzw.myapp.R;

public class StockQuoteClient extends Activity {

    private static final String TAG="StockQuoteClient";
    private IStockQuoteService stockService=null;
    private ToggleButton bindBtn;
    private Button callBtn;

    private IStockQuoteService2 stockService2=null;
    private ToggleButton bindBtnComplex;
    private Button callBtnComplex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_stock_quote);

        bindBtn=(ToggleButton)findViewById(R.id.bindBtn);
        callBtn=(Button)findViewById(R.id.callBtn);

        bindBtnComplex=(ToggleButton)findViewById(R.id.bindBtnComplex);
        callBtnComplex=(Button)findViewById(R.id.callBtnComplex);
    }


    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bindBtn:
                if(((ToggleButton)view).isChecked())
                {
                   /* Intent intent=new Intent(this,IStockQuoteService.class);
                    intent.setPackage(IStockQuoteService.class.getPackage().getName());*/
                    getApplicationContext().bindService(new Intent(this,IStockQuoteService.class),serConn, Context.BIND_AUTO_CREATE);
                }
                else
                {
                    unbindService(serConn);
                    callBtn.setEnabled(false);
                }
                break;
            case R.id.callBtn:
                callService();
                break;
            case R.id.bindBtnComplex:
                if(((ToggleButton)view).isChecked())
                {
                    Intent intent=new Intent(IStockQuoteService2.class.getName());
                    intent.setPackage("com.example.lzw.myapp");
                    startService(intent);
                    bindService(intent,serConn2, Context.BIND_AUTO_CREATE);
                }
                else
                {
                    unbindService(serConn2);
                    callBtnComplex.setEnabled(false);
                }
                break;
            case R.id.callBtnComplex:
                callService2();
                break;
        }
    }

    private void callService()
    {
        try {
            double val=stockService.getQuote("ANDROID");
            Toast.makeText(StockQuoteClient.this,"Value from service is "+val,Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            Log.e("StockQuoteClient",e.getMessage(),e);
        }
    }

    private ServiceConnection serConn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(TAG,"onServiceConnected() called");
            stockService=IStockQuoteService.Stub.asInterface(iBinder);
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

    private void callService2()
    {
        try {
            Person person=new Person();
            person.setAge(47);
            person.setName("Dave");
            String response =stockService2.getQuote("ANDROID",person);
            Toast.makeText(StockQuoteClient.this,"Value from service is "+response,Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            Log.e("StockQuoteClient",e.getMessage(),e);
        }
    }

    private ServiceConnection serConn2=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(TAG,"onService2Connected() called");
            stockService2=IStockQuoteService2.Stub.asInterface(iBinder);
            bindBtnComplex.setChecked(true);
            callBtnComplex.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(TAG,"onService2Disconnected() called");
            bindBtnComplex.setChecked(false);
            callBtnComplex.setEnabled(false);
            stockService2=null;
        }
    };

    protected void onDestroy()
    {
        Log.v(TAG,"onDestroy() called");
        if(callBtn.isEnabled())
        {
            unbindService(serConn);
        }

        if(callBtnComplex.isEnabled())
        {
            unbindService(serConn2);
        }
        super.onDestroy();
    }
}
