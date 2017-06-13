package com.example.lzw.myapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/05/31.
 */
public class MyLongTaskWithFragment extends AsyncTask<String,Integer,Integer> implements IFragmentDialogCallbacks,IWorkerObject {
    public String tag=null;
    public static String PROGRESS_DIALOG_FRAGMENT_TAG_NAME="AsyncDialog";

    public MonitoredFragment retainedFragment;
    private boolean bDoneFlag=false;

    private IReportBack r;

    MyLongTaskWithFragment(MonitoredFragment parentFragment,IReportBack inr,String inTag)
    {
        tag=inTag;
        retainedFragment=parentFragment;
        r=inr;
    }

    protected void onPreExecute()
    {
        Utils.logThreadSignature(this.tag);
        Log.d(tag,"bDoneFlag:"+bDoneFlag);
        showDialogFragment();
    }

    private void showDialogFragment()
    {
        Activity act=retainedFragment.getActivity();
        ProgressDialogFragment pdf=new ProgressDialogFragment();
        pdf.show(act.getFragmentManager(),this.PROGRESS_DIALOG_FRAGMENT_TAG_NAME);
    }

    private ProgressDialogFragment getDialog()
    {
        Activity act=retainedFragment.getActivity();
        if(act==null)
        {
            Log.d(tag,"activity is null.shouldnt be.returning a null dialog");
            return null;
        }

        return (ProgressDialogFragment)act.getFragmentManager().findFragmentByTag(this.PROGRESS_DIALOG_FRAGMENT_TAG_NAME);
    }


    protected void onProgressUpdate(Integer... progress)
    {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();

        Integer i=progress[0];

        if(retainedFragment.isbUIReady())
        {
            r.reportBack(tag,"Progress:"+i.toString());
            setProgressOnProgressDialog(i);
        }
        else
        {
            Log.d(tag,"Activity is not ready!Progress update not displayed:"+i);
        }
    }

    private void setCallbacksOnProgressDialog()
    {
        ProgressDialogFragment dialog=getDialog();
        if(dialog==null)
        {
            Log.d(tag,"Dialog is not available to set callbacks");
            return;
        }
        dialog.setDialogFragmentCallbacks(this);
    }

    private void setProgressOnProgressDialog(int i)
    {
        ProgressDialogFragment dialog=getDialog();
        if(dialog==null)
        {
            Log.d(tag,"Dialog is not available to set progress");
            return;
        }
        dialog.setProgress(i);
    }

    private void closeProgressDialog()
    {
        DialogFragment dialog=getDialog();
        if(dialog==null)
        {
            Log.d(tag,"Sorry dialog fragment is null to close it!");
            return;
        }

        Log.d(tag,"Dialog is being dismissed");
        dialog.dismiss();
    }

    protected void onPostExecute(Integer result)
    {
        bDoneFlag=true;
        Utils.logThreadSignature(this.tag);
        conditionalReportBack("onPostExecute result:"+result);

        if(retainedFragment.isbUIReady())
        {
            Log.d(tag,"UI is ready and running.dismiss is pemissible");
            closeProgressDialog();
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
        setCallbacksOnProgressDialog();
        if(bDoneFlag==true)
        {
            Log.d(tag,"On my start I notice I was done earlier");
            closeProgressDialog();
        }
    }

    public void onCancel(DialogFragment df, DialogInterface di)
    {
        Log.d(tag,"onCancel called");
    }

    public void onDismiss(DialogFragment df,DialogInterface di)
    {
        Log.d(tag,"onDismiss called");
        Log.d(tag,"Remove myself from my parent");
        detachFromParent();
    }

    IWorkerObjectClient client=null;
    int workerObjectPassbackIdentifier=-1;
    private void detachFromParent()
    {
        if(client==null)
        {
            Log.e(tag,"You have failed to register a client.");
            return;
        }

        client.done(this,workerObjectPassbackIdentifier);
    }

    public void registerClient(IWorkerObjectClient woc,int inWorkerObjectPassbackIdentifier)
    {
        Log.d(tag,"Registering a client for the asynctask");
        client=woc;
        this.workerObjectPassbackIdentifier=inWorkerObjectPassbackIdentifier;
    }

    public void releaseResources()
    {
        Log.d(tag,"Cancelling the task called");
        cancel(true);
        detachFromParent();
    }
}
