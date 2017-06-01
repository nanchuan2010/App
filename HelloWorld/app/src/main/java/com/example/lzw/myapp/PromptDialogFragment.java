package com.example.lzw.myapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by LZW on 2016-08-29.
 */
public class PromptDialogFragment extends DialogFragment
            implements View.OnClickListener
{
    public static PromptDialogFragment newInstance(String prompt)
    {
        PromptDialogFragment pdf=new PromptDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("prompt",prompt);
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
           // Log.e(MainActivity.LOGTAG,"Activity is not listening");
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.prompt_dialog,container,false);

        TextView tv=(TextView)v.findViewById(R.id.promptmessage);
        tv.setText(getArguments().getString("prompt"));

        Button dismissBtn=(Button)v.findViewById(R.id.btn_dismiss);
        dismissBtn.setOnClickListener(this);

        Button saveBtn=(Button)v.findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(this);

        Button helpBtn=(Button)v.findViewById(R.id.btn_help);
        helpBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        //Log.v(MainActivity.LOGTAG,"in onCancel() of PDF");
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
       // Log.v(MainActivity.LOGTAG,"in onDismissing() of PDF");
        super.onDismiss(dialog);
    }

    public void onClick(View v)
    {
        OnDialogDoneListener act=(OnDialogDoneListener)getActivity();
        if(v.getId()==R.id.btn_save)
        {
            TextView tv=(TextView)getView().findViewById(R.id.inputtext);
            act.onDialogDone(this.getTag(),false,tv.getText());
            dismiss();
            return;
        }

        if(v.getId()==R.id.btn_dismiss)
        {
            act.onDialogDone(this.getTag(),true,null);
            dismiss();
            return;
        }
        if(v.getId()==R.id.btn_help)
        {
            FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.remove(this);

            ft.addToBackStack(null);

            HelpDialogFragment hdf=HelpDialogFragment.newInstance(R.string.help_text);
            //hdf.show(ft,MainActivity.HELP_DIALOG_TAG);
            return;
        }
    }
}
