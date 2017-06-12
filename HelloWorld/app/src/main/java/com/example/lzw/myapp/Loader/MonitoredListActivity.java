package com.example.lzw.myapp.Loader;

import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/10.
 */

public class MonitoredListActivity extends ListActivity {
    private String tag=null;

    MonitoredListActivity(String intag)
    {
        tag=intag;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(tag,"onCreate.");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        Log.d(tag,"onpause.I may be partitally or fully invisible");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(tag,"onstop.I am fully invisible");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(tag,"ondestroy.about to be removed");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(tag,"onRestart. UI controls are there.");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(tag,"onResume. UI fully visible.");
        super.onResume();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(tag,"onRestoreInstanceState.You should restore state");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        Log.d(tag,"onRetainNonConfigurationInstance.");
        return super.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(tag,"onSaveInstanceState.You should load up the bundle");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(tag,"onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
