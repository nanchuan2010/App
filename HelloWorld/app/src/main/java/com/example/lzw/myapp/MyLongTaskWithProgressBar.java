package com.example.lzw.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/05/31.
 */
public class MyLongTaskWithProgressBar extends AsyncTask<String,Integer,Integer> implements IWorkerObject {
    public String tag=null;
    private MonitoredFragment retainedFragment;
    int curProgress=0;

    private IReportBack r;

    MyLongTaskWithProgressBar(MonitoredFragment parentFragment,IReportBack inr,String inTag)
    {
        tag=inTag;
        retainedFragment=parentFragment;
        r=inr;
    }

    protected void onPreExecute()
    {
        Utils.logThreadSignature(this.tag);
        showProgressBar();
    }

    private void showProgressBar()
    {
        Activity act=retainedFragment.getActivity();
        ProgressBar pb=(ProgressBar)act.findViewById(R.id.tpb_progressBar1);
        pb.setProgress(0);
        pb.setMax(15);
        pb.setVisibility(View.VISIBLE);
    }

    private ProgressBar getProgressBar()
    {
        Activity act=retainedFragment.getActivity();
        if(act==null)
        {
            Log.d(tag,"activity is null.shouldnt be.returning a null dialog");
            return null;
        }

        return (ProgressBar)act.findViewById(R.id.tpb_progressBar1);
    }

    protected void onProgressUpdate(Integer... progress)
    {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();

        Integer i=progress[0];
        if(retainedFragment.isbUIReady())
        {
            r.reportBack(tag,"Progress:"+i.toString());
            setProgressOnProgressBar(i);
        }
        else
        {
            Log.d(tag,"Activity is not ready!Progress update not displayed:"+i);
        }
    }

    private void setProgressOnProgressBar(int i)
    {
        this.curProgress=i;
        ProgressBar pbar=getProgressBar();
        if(pbar==null)
        {
            Log.d(tag,"Activity is not available to set progress");
            return;
        }
        r.reportBack(tag,"pbar:"+pbar.getProgress());
        pbar.setProgress(i);
    }

    private void closeProgressBar()
    {
        ProgressBar pbar=getProgressBar();
        if(pbar==null)
        {
            Log.d(tag,"Sorry progress bar is null to close it!");
            return;
        }

        Log.d(tag,"Progress bar is being dismissed");
        pbar.setVisibility(View.GONE);
        detachFromParent();
    }

    private boolean bDoneFlag=false;
    protected void onPostExecute(Integer result)
    {
        bDoneFlag=true;
        Utils.logThreadSignature(this.tag);
        conditionalReportBack("onPostExecute result:"+result);

        if(retainedFragment.isbUIReady())
        {
            Log.d(tag,"UI is ready and running.dismiss is pemissible");
            closeProgressBar();
            return;
        }

        Log.d(tag,"UI is not ready.Do this on start again");
    }


    protected Integer doInBackground(String... strings)
    {
        Utils.logThreadSignature(this.tag);
        for (String s:strings)
        {
            Log.d(tag,"Processing:"+s);
        }

        for (int i=0;i<15;i++)
        {
            Utils.sleepForInSecs(2);
            publishProgress(i);
        }

        return 1;
    }

    protected void reportThreadSignature()
    {
        String s=Utils.getThreadSignature();
        conditionalReportBack(s);
    }

    private void conditionalReportBack(String message)
    {
        Log.d(tag,message);
        r.reportBack(tag,message);
    }

    public void onStart(Activity act)
    {
        if(bDoneFlag==true)
        {
            Log.d(tag,"On my start I notice I was done earlier");
            closeProgressBar();
            return;
        }
        Log.d(tag,"I am reattached.I am not done");
        setProgressBarRightOnReattach();
    }

    private void setProgressBarRightOnReattach()
    {
        ProgressBar pb=getProgressBar();
        pb.setMax(15);
        pb.setProgress(curProgress);
        pb.setVisibility(View.VISIBLE);
    }

    IWorkerObjectClient client=null;
    int workerObjectPassbackIdentifier=-1;

    public void registerClient(IWorkerObjectClient woc,int inWorkerObjectPassbackIdentifier)
    {
        Log.d(tag,"Registering a client for the asynctask");
        client=woc;
        this.workerObjectPassbackIdentifier=inWorkerObjectPassbackIdentifier;
    }

    public void releaseResources()
    {
        Log.d(tag,"Cancelling the task");
        cancel(true);
        detachFromParent();
    }

    private void detachFromParent()
    {
        if(client==null)
        {
            Log.e(tag,"You have failed to register a client.");
            return;
        }
        client.done(this,workerObjectPassbackIdentifier);
    }
}
