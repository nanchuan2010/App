package com.example.lzw.myapp.AsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by LZW on 2017/05/31.
 */
public class MonitoredActivity extends Activity {
    protected String tag = null;

    protected MonitoredActivity(String intag) {
        tag = intag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, "onCreate.");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        Log.d(tag, "onpause. I may be partially or fully invisible");
        if (isFinishing() == true) {
            Log.d(tag, "The activity is closing by expectation. isFinishing() is true");
        } else {
            Log.d(tag, "The activity is closing by force or on a config change.isFinishing() is false");
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(tag, "onstop. I am fully invisible");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(tag, "ondestroy. about to be removed");
        if (isFinishing() == true) {
            Log.d(tag, "The activity is closing by expectation. isFinishing() is true,like on a back");
            releaseResources();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(tag, "onRestart. UI controls are there.");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(tag, "onStart.UI may be partially visible");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(tag, "onResume.UI fully visible.");
        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(tag, "onRestoreInstanceState.You should restore state");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        Log.d(tag, "onRetainNonConfigurationInstance.");
        return super.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(tag, "onSaveInstanceState.You should load up the bundle");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(tag, "onConfigurationChanged.No rotation occurs.");
        super.onConfigurationChanged(newConfig);
    }


    protected void releaseResources() {
        Log.d(tag, "Release Resources called");
    }

    public void startTargetActivity(Class<? extends Activity> targetActivityClass) {
        Intent i = new Intent(this, targetActivityClass);
        startActivity(i);
    }
}
