package com.example.lzw.myapp.AsyncTask;

import android.os.Bundle;
import android.util.Log;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by LZW on 2017/05/31.
 */
public class BaseTesterFragment extends MonitoredFragment implements IReportBack {

    public BaseTesterFragment() {
    }

    public void init(String tagname, Bundle argsBundle) {
        super.init(tagname, argsBundle);
    }

    @Override
    public void reportBack(String tag, String message) {
        if (!isbUIReady()) {
            Log.d(tag, "sorry activity is not ready during reportback");
        }

        getReportBack().reportBack(tag, message);
    }

    private IReportBack getReportBack() {
        return (IReportBack) getActivity();
    }

    @Override
    public void reportTransient(String tag, String message) {
        if (!isbUIReady()) {
            Log.d(tag, "sorry activity is not ready during reportback");
            return;
        }

        getReportBack().reportBack(tag, message);
    }
}
