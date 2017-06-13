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
public class MyLongTaskWithRADO extends AsyncTask<String,Integer,Integer> implements IRetainedADO,IFragmentDialogCallbacks {
    public String tag=null;
    public static String PROGRESS_DIALOG_FRAGMENT_TAG_NAME="AsyncDialog";

    private IRetainedADO retainedADOImpl;

    private IReportBack r;

    MyLongTaskWithRADO(IRetainedADO parentRADO,IReportBack inr,String inTag)
    {
        tag=inTag;
        retainedADOImpl=new RetainedADO(parentRADO,inTag,this);
        r=inr;
    }

    protected void onPreExecute()
    {
        Utils.logThreadSignature(this.tag);
        showDialogFragment();
    }

    private void showDialogFragment()
    {
        Activity act=this.getActivity();
        ProgressDialogFragment pdf=new ProgressDialogFragment();
        pdf.show(act.getFragmentManager(),this.PROGRESS_DIALOG_FRAGMENT_TAG_NAME);
    }

    private ProgressDialogFragment getDialog()
    {
        Activity act=getActivity();
        if(act==null)
        {
            Log.d(tag,"activity is null.shouldnt be. returning a null dialog");
            return null;
        }

        return (ProgressDialogFragment)act.getFragmentManager().findFragmentByTag(this.PROGRESS_DIALOG_FRAGMENT_TAG_NAME);
    }

    protected void onProgressUpdate(Integer... progress)
    {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();
        Integer i=progress[0];

        if(isActivityReady())
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
            return ;
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

    private boolean bDoneFlag=false;
    protected void onPostExecute(Integer result)
    {
        bDoneFlag=true;
        Utils.logThreadSignature(this.tag);
        conditionalReportBack("onPostExecute result:"+result);

        if(isUIReady())
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
            Log.d(tag,"Progressing:"+s);
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
        String s= Utils.getThreadSignature();
        conditionalReportBack(s);
    }

    public void onCancel(DialogInterface d)
    {
        conditionalReportBack("Cancel Called");
        this.cancel(true);
    }

    private void conditionalReportBack(String message) {
        Log.d(tag,message);
        r.reportBack(tag,message);
    }

    public MonitoredActivityWithADOSupport getActivity()
    {
        return retainedADOImpl.getActivity();
    }

    public void reset()
    {
        retainedADOImpl.reset();
    }

    public void attach(MonitoredActivityWithADOSupport act)
    {
        retainedADOImpl.attach(act);
        setCallbacksOnProgressDialog();

        if(bDoneFlag==true)
        {
            Log.d(tag,"On my start I notice I was done earlier");
            closeProgressDialog();
        }
    }

    public void releaseContracts()
    {
        retainedADOImpl.releaseContracts();
    }


    public boolean isActivityReady()
    {
        return retainedADOImpl.isActivityReady();
    }

    public void addChildRetainedADO(IRetainedADO childRetainedADO)
    {
        retainedADOImpl.addChildRetainedADO(childRetainedADO);
    }

    public boolean isUIReady()
    {
        return retainedADOImpl.isUIReady();
    }

    public boolean isConfigurationChanging()
    {
        return retainedADOImpl.isConfigurationChanging();
    }


    public void removeChildRetainedADO(IRetainedADO childRetainedADO)
    {
        retainedADOImpl.removeChildRetainedADO(childRetainedADO);
    }

    public void removeAllChildRetainedADOs()
    {
        retainedADOImpl.removeAllChildRetainedADOs();
    }

    public String getName()
    {
        return retainedADOImpl.getName();
    }

    public void detachFromParent()
    {
        retainedADOImpl.detachFromParent();
    }

    public void logStatus()
    {
        retainedADOImpl.logStatus();
    }

    public void addChildRetainedADOOnly(IRetainedADO childRetainedADO)
    {
        retainedADOImpl.addChildRetainedADOOnly(childRetainedADO);
    }

    public void onCancel(DialogFragment df,DialogInterface di)
    {
        Log.d(tag,"onCancel called.");
    }

    public void onDismiss(DialogFragment df,DialogInterface di)
    {
        Log.d(tag,"onDismiss called");
        Log.d(tag,"Remove myself from my parent");
        detachFromParent();
    }
}
