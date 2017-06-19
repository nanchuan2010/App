package com.example.lzw.myapp.Controls;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

/**
 * Created by Administrator on 2017/6/16.
 */

public class SimpleSpinnerArrayAdapter extends ArrayAdapter<String> implements SpinnerAdapter {
    public SimpleSpinnerArrayAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item,new String[]{"one","two"});
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

}
