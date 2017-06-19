package com.example.lzw.myapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2016/08/20.
 */
public class DetailsFragment extends Fragment {
    private int mIndex=0;

    public static DetailsFragment newInstance(int index)
    {
        Log.v(FragmentActivity.TAG,"in DetailsFragment newInstance("+index+")");

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
    public void onCreate(Bundle bundle) {
        Log.v(FragmentActivity.TAG,"in DetailsFragment onCreate. Bundle contains");
        if(bundle!=null)
        {
            for (String key:bundle.keySet())
            {
                Log.v(FragmentActivity.TAG,"  "+key);
            }
        }
        else
        {
            Log.v(FragmentActivity.TAG,"    myBundle is null");
        }
        super.onCreate(bundle);

        mIndex=getArguments().getInt("index",0);
    }

    public int getShownIndex()
    {
        return mIndex;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(FragmentActivity.TAG,"in DetailsFragment onCreateView. Container="+container);
        View v=inflater.inflate(R.layout.frm_details,container,false);
        TextView text1=(TextView)v.findViewById(R.id.text1);
        text1.setText(Shakespeare.DIALOG[mIndex]);
        return v;
    }
}

