package com.example.lzw.myapp;

import android.os.Bundle;
import android.util.Log;

import com.example.lzw.myapp.AsyncTask.MonitoredActivity;
import com.example.lzw.myapp.AsyncTask.RetainedADO;

/**
 * Created by LZW on 2017/05/31.
 */
public class MonitoredActivityWithADOSupport extends MonitoredActivity {
    private RetainedADO mRootRADO = null;

    private boolean bUIReady = true;

    public MonitoredActivityWithADOSupport(String intag) {
        super(intag);
    }

    protected RetainedADO getRootRADO() {
        if (mRootRADO == null) {
            Log.w(tag, "you probably have forgotten to override onCreateRADO!");

        }
        return mRootRADO;
    }


    @Override
    protected void onStop() {
        super.onStop();
        bUIReady = false;
        if (mRootRADO != null) {

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRADO = obtainRootRADO();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bUIReady = true;
        if (mRootRADO == null) {
            Log.d(tag, "Root RADO is null");
        } else {
            Log.d(tag, "Root RADO exists.attaching the parent activity");
            mRootRADO.attach(this);
        }
    }

    private RetainedADO obtainRootRADO() {
        if (mRootRADO != null) {
            return mRootRADO;
        }

        Object rootRADO = this.getLastNonConfigurationInstance();
        if (rootRADO == null) {
            rootRADO = onCreateRADO();
            return (RetainedADO) rootRADO;
        }
        if (!(rootRADO instanceof RetainedADO)) {
            Log.e(tag, "You are returning non RetainedADO");
            return null;
        }

        return (RetainedADO) rootRADO;
    }

    protected RetainedADO onCreateRADO() {
        return null;
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        Log.d(tag, "onRetainNonConfigurationInstance.");
        if (mRootRADO != null) {
            Log.d(tag, "mRootRADO is not null.Resetting its activity");
            mRootRADO.reset();
        }
        return mRootRADO;
    }


    protected void releaseResources() {
        if (mRootRADO != null) {
            Log.d(tag, "Releasing root RADO resources");
            mRootRADO.releaseContracts();
        }
    }

    public boolean isUIReady() {
        return bUIReady;
    }
}
