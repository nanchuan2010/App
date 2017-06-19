package com.example.lzw.myapp.Controls;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

import com.example.lzw.myapp.BaseListener;
import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/16.
 */

public class TabListener   implements ActionBar.TabListener{

    private static String tag="TabListener>";
    private Context mContext=null;

    public TabListener(Context ctx) {
        this.mContext=ctx;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Toast.makeText(mContext,"ontab selected:"+tab.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Toast.makeText(mContext,"ontab un selected:"+tab.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Toast.makeText(mContext,"ontab re selected:"+tab.getText(),Toast.LENGTH_SHORT).show();
    }
}
