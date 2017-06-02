package com.example.lzw.myapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by LZW on 2017/05/31.
 */
public class ProgressDialogFragment extends DialogFragment {
    private static String tag="ProgressDialogFragment";
    ProgressDialog pd;

    private IFragmentDialogCallbacks fdc;
    public void setDialogFragmentCallbacks(IFragmentDialogCallbacks infdc)
    {
        Log.d(tag,"attaching dialog callbacks");
        fdc=infdc;
    }


    public ProgressDialogFragment()
    {
        this.setCancelable(false);
    }

    public ProgressDialogFragment(IFragmentDialogCallbacks infdc)
    {
        this.fdc=infdc;
        this.setCancelable(false);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(tag,"In onCreateDialog");
        pd=new ProgressDialog(getActivity());
        pd.setTitle("title");
        pd.setMessage("In Progress...");
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(15);
        return pd;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(tag,"Dialog dismissed");
        if(fdc!=null)
        {
            fdc.onDismiss(this,dialog);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(tag,"Dialog cancelled");
        if(fdc!=null)
        {
            fdc.onCancel(this,dialog);
        }
    }


    public void setProgress(int value)
    {
        pd.setProgress(value);
    }
}
