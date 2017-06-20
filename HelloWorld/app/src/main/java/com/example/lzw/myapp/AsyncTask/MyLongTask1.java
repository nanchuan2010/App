package com.example.lzw.myapp.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/05/25.
 */
public class MyLongTask1 extends AsyncTask<String, Integer, Integer> {
    IReportBack r;
    Context ctx;
    public String tag = null;
    ProgressDialog pd = null;

    MyLongTask1(IReportBack inr, Context inCtx, String inTag) {
        r = inr;
        ctx = inCtx;
        tag = inTag;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Utils.logThreadSignature(this.tag);
        for (String s : strings) {
            Log.d(tag, "Processing:" + s);
        }

        for (int i = 0; i < 3; i++) {
            Utils.sleepForInSecs(2);
            publishProgress(i);
        }

        return 1;
    }

    @Override
    protected void onPreExecute() {
        Utils.logThreadSignature(this.tag);
        pd = ProgressDialog.show(ctx, "title", "In Progress...", true);
    }

    protected void onProgressUpdate(Integer... progress) {
        Utils.logThreadSignature(this.tag);
        Integer i = progress[0];
        r.reportBack(tag, "Progress:" + i.toString());
    }

    protected void onPostExecute(Integer result) {
        //Runs on the services_main_activity ui thread
        Utils.logThreadSignature(this.tag);
        r.reportBack(tag, "onPostExecute result:" + result);
        pd.cancel();
    }

}
