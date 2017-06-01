package com.example.lzw.myapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LZW on 2016/08/20.
 */
public class DetailsFragment extends Fragment {
    private int mIndex=0;

    public static DetailsFragment newInstance(int index)
    {
        Log.v(ExampleActivity.TAG,"in DetailsFragment newInstance("+index+")");

        DetailsFragment df=new DetailsFragment();
        Bundle args=new Bundle();
        args.putInt("index",index);
        df.setArguments(args);
        return df;
    }

    public static DetailsFragment newInstance(Bundle bundle)
    {
        int index=bundle.getInt("index",0);
        return newInstance(index);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(ExampleActivity.TAG,"in DetailsFragment onCreate. Bundle contains");
        if(savedInstanceState!=null)
        {
            for (String key:savedInstanceState.keySet())
            {
                Log.v(ExampleActivity.TAG,"  "+key);
            }
        }
        else
        {
            Log.v(ExampleActivity.TAG,"    myBundle is null");
        }
        super.onCreate(savedInstanceState);

        mIndex=getArguments().getInt("index",0);
    }

    public int getShownIndex()
    {
        return mIndex;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(ExampleActivity.TAG,"in DetailsFragment onCreateView. Container="+container);
        View v=inflater.inflate(R.layout.details,container,false);
        TextView text1=(TextView)v.findViewById(R.id.text1);
        text1.setText(Shakespeare.DIALOG[mIndex]);
        return v;
    }
}

 class Shakespeare{
    public static String TITLES[]={
            "Henry IV",
            "Henry IVI",
            "Henry IV4",
            "Henry IV3",
            "Henry IV333"
    };
    public static String DIALOG[]={
            "aaHenry IV",
            "aaHenry IVI",
            "aaaHenry IV4",
            "aaaHenry IV3",
            "aaaHenry IV333"
    };
}
