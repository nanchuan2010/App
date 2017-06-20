package com.example.lzw.myapp.AsyncTask;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.lzw.myapp.MonitoredActivityWithADOSupport;

/**
 * Created by LZW on 2017/05/31.
 */
public class AsyncTesterFragment extends BaseTesterFragment implements IWorkerObjectClient {
    private final static String tag = "AsyncTesterFragment";
    private final static String FRAGMENT_TAG = "RetainedAsyncTesterFragment";

    MyLongTaskWithFragment longTaskWithFragment = null;

    public AsyncTesterFragment() {
    }

    public void init() {
        Bundle b = new Bundle();
        super.init(tag, b);
    }

    public static AsyncTesterFragment newInstance() {
        AsyncTesterFragment i = new AsyncTesterFragment();
        i.init();
        return i;
    }


    @Override
    public void onStart() {
        super.onStart();
        MonitoredActivityWithADOSupport act = (MonitoredActivityWithADOSupport) getActivity();
        if (longTaskWithFragment != null) {
            longTaskWithFragment.onStart(act);
        }
    }

    public void testFragmentProgressDialog() {
        longTaskWithFragment = new MyLongTaskWithFragment(this, this, "Task With Dialogs");
        longTaskWithFragment.registerClient(this, 0);
        longTaskWithFragment.execute("String1", "String2", "String3");
    }

    public static AsyncTesterFragment createRetainedAsyncTesterFragment(Activity act) {
        Log.d(tag, "Creating the async tester fragment");
        AsyncTesterFragment frag = AsyncTesterFragment.newInstance();
        frag.setRetainInstance(true);
        FragmentManager fm = act.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(frag, AsyncTesterFragment.FRAGMENT_TAG);
        ft.commit();
        return frag;
    }

    public static AsyncTesterFragment establishRetainedAsyncTesterFragment(Activity act) {
        AsyncTesterFragment frag = getRetainedAsyncTesterFragment(act);
        if (frag == null) {
            return createRetainedAsyncTesterFragment(act);
        }
        Log.d(tag, "Fragment is already there");
        return frag;
    }

    public static AsyncTesterFragment getRetainedAsyncTesterFragment(Activity act) {
        Fragment frag = act.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (frag == null) {
            Log.d(tag, "Fragment doesn't exist");
            return null;
        }

        return (AsyncTesterFragment) frag;
    }

    @Override
    public void done(IWorkerObject wobj, int workerObjectPassthroughIdentifier) {
        if (workerObjectPassthroughIdentifier == 0) {
            Log.d(tag, "The asynctask has finished .the dialog is closed.release the pointer");
            this.longTaskWithFragment = null;
        } else if (workerObjectPassthroughIdentifier == 1) {
            Log.d(tag, "The asynctask with pbar has finished.release the pointer");
            //this.longTaskWithFragment=
        }


    }

    public void releaseResources() {
        Log.d(tag, "Fragment release resources");
        // if(longTaskWithFragment)
    }
}
