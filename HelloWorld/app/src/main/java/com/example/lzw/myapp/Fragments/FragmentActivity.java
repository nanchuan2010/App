package com.example.lzw.myapp.Fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.lzw.myapp.R;

public class FragmentActivity extends Activity{

    public   static final String TAG= "FRAGMENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_layout_activity);
    }

    public boolean isMultiPane()
    {
        return getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
    }

    public void showDetails(int index)
    {
        Log.v(TAG,"in MainActivity showDetails("+index+")");
        if(isMultiPane())
        {
            DetailsFragment details=(DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
            if((details==null)||(details.getShownIndex()!=index))
            {
                details=DetailsFragment.newInstance(index);
                Log.v(TAG,"about to run FragmentTransaction...");

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
                //ft.addToBackStack("details");
                ft.replace(R.id.details,details);
                ft.commit();
            }
        }
        else
        {
            Intent intent=new Intent();
            intent.setClass(this,DetailsActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }


}

