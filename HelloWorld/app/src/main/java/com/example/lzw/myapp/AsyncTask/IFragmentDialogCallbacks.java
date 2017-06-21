package com.example.lzw.myapp.AsyncTask;

import android.app.DialogFragment;
import android.content.DialogInterface;

/**
 * Created by LZW on 2017/05/31.
 */
public interface IFragmentDialogCallbacks {
    public void onCancel(DialogFragment df, DialogInterface di);
    public void onDismiss(DialogFragment df, DialogInterface di);
}
