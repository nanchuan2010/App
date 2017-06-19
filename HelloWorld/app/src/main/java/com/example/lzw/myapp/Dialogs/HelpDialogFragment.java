package com.example.lzw.myapp.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2016-08-29.
 */
public class HelpDialogFragment extends DialogFragment
implements View.OnClickListener{
    public static HelpDialogFragment newInstance(int helpResId)
    {
        HelpDialogFragment hdf=new HelpDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("help_resource",helpResId);
        hdf.setArguments(bundle);

        return hdf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        int style=DialogFragment.STYLE_NORMAL,theme=0;
        setStyle(style,theme);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Help Dialog");
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.dlg_help_dialog,container,false);
        TextView tv=(TextView)v.findViewById(R.id.helpmessage);
        tv.setText(getActivity().getResources().getText(getArguments().getInt("help_resource")));

        Button closeBtn=(Button)v.findViewById(R.id.btn_close);
        closeBtn.setOnClickListener(this);
        return v;
    }

    public void onClick(View v)
    {
        dismiss();
    }
}
