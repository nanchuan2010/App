package com.example.lzw.myapp.Services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2016/09/14.
 */
public class ServiceActivity extends Activity {
    private static final String TAG="ServiceActivity";
    private int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_local_activity);
    }

    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.startBtn:
                Log.v(TAG,"Starting service... counter="+counter);
                Intent intent=new Intent(ServiceActivity.this,BackgroundService.class);
                intent.putExtra("counter",counter++);
                startService(intent);
                break;
            case R.id.stopBtn:
                stopService();
        }
    }

    private void stopService() {
        Log.v(TAG,"Stopping service...");
        if(stopService(new Intent(ServiceActivity.this,BackgroundService.class)))
            Log.v(TAG,"stopService was successful");
        else
            Log.v(TAG,"stopService was unsuccessful");
    }

    @Override
    protected void onDestroy() {
        stopService();
        super.onDestroy();
    }
}
