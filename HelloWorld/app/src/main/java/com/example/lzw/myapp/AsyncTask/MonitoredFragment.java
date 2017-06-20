package com.example.lzw.myapp.AsyncTask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LZW on 2017/05/31.
 */
public class MonitoredFragment extends Fragment {
    public static String DEBUG_TAG_NAME = "com.androidbook.asynctask.debugtag";
    private String tag = "No tag specified yet.Still being constructed";

    boolean bUIReady = false;

    public MonitoredFragment() {
    }

    public void init(String tagname, Bundle argsBundle) {
        argsBundle.putString(this.DEBUG_TAG_NAME, tagname);
        this.setArguments(argsBundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reConstructTagNameFromArgsBundle();
        Log.d(tag, "onAttach called.Very first call back");
    }

    private void reConstructTagNameFromArgsBundle() {
        Bundle args = this.getArguments();
        if (args == null) {
            Log.w(tag, "Sorry no args set to collect the tag name");
            return;
        }

        String tagname = args.getString(this.DEBUG_TAG_NAME);
        if (tagname == null) {
            Log.w(tag, "Sorry no debug tag specified");
            return;
        } else {
            tag = tagname;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "onCreate called.Activity creation hasn't finished yet.");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag, "onActivityCreated called.All views including activity are ready.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(tag, "onstart");
        bUIReady = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(tag, "onConfigChanged");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
        if (getActivity().isFinishing() == true) {
            releaseResources();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(tag, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(tag, "onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(tag, "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(tag, "onstop");
        bUIReady = false;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(tag, "onTrimMemory. level:" + level);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public boolean isActivityReady() {
        Activity act = getActivity();
        if (act != null)
            return true;
        return false;
    }

    public boolean isbUIReady() {
        return bUIReady;
    }

    public void releaseResources() {
        Log.d(tag, "Fragment release resources");
    }
}
