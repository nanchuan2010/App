package com.example.lzw.myapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by LZW on 2017/05/26.
 */
public class MyLongTask1 extends AsyncTask<String,Integer,Integer> implements DialogInterface.OnCancelListener {
    IReportBack r;
    Context ctx;
    public String tag = null;
    ProgressDialog pd = null;

    MyLongTask1(IReportBack inr, Context inCtx, String inTag) {
        r = inr; ctx = inCtx; tag = inTag;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Utils.logThreadSignature(this.tag);
        for (String s:strings)
        {
            Log.d(tag,"Processing:"+s);
        }

        for (int i=0;i<5;i++)
        {
            Utils.sleepForInSecs(2);
            publishProgress(i);
        }

        return 1;
    }

    @Override
    protected void onPreExecute() {
        Utils.logThreadSignature(this.tag);
        pd=new ProgressDialog(ctx);
        pd.setTitle("title");
        pd.setMessage("In Progress...");
        pd.setCancelable(true);
        pd.setOnCancelListener(this);
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(5);
        pd.show();
    }

    protected void onProgressUpdate(Integer... progress) {
        Utils.logThreadSignature(this.tag);
        this.reportThreadSignature();

        Integer i = progress[0];
        r.reportBack(tag, "Progress:" + i.toString());
        pd.setProgress(i);
    }

    protected void onPostExecute(Integer result) {
        //Runs on the main ui thread
        Utils.logThreadSignature(this.tag);
        r.reportBack(tag, "onPostExecute result:" + result);
        pd.cancel();
    }

    protected void reportThreadSignature()
    {
        String s=Utils.getThreadSignature();
        r.reportBack(tag,s);
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        r.reportBack(tag,"Cancel called");
        this.cancel(true);
    }
}
