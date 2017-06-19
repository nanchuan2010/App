package com.example.lzw.myapp.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by LZW on 2016-08-29.
 */
public class AlertDialogFragment extends DialogFragment
implements DialogInterface.OnClickListener{

    public static AlertDialogFragment newInstance(String message)
    {
        AlertDialogFragment pdf=new AlertDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("alert-message",message);
        pdf.setArguments(bundle);

        return pdf;
    }

    @Override
    public void onAttach(Activity activity) {
        try
        {
            OnDialogDoneListener test=(OnDialogDoneListener)activity;
        }
        catch (ClassCastException cce)
        {
            Log.e(DialogActivity.LOGTAG,"Activity is not listening");
        }

        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        int style=DialogFragment.STYLE_NORMAL,theme=0;
        setStyle(style,theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b=new AlertDialog.Builder(getActivity())
                .setTitle("Alert!!!")
                .setPositiveButton("OK",this)
                .setNegativeButton("Cancel",this)
                .setMessage(this.getArguments().getString("alert-message"));

        return b.create();
    }

    public void onClick(DialogInterface dialog,int whick)
    {
        OnDialogDoneListener act=(OnDialogDoneListener)getActivity();
        boolean cancelled=false;
        if(whick==AlertDialog.BUTTON_NEGATIVE)
        {
            cancelled=true;
        }

        act.onDialogDone(getTag(),cancelled,"Alert dismissed");
    }



}
