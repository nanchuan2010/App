package com.example.lzw.myapp.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by LZW on 2016/08/20.
 */
public class DetailsActivity extends android.support.v4.app.FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(FragmentActivity.TAG,"in DetailsActivity onCreate");
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }
        if(getIntent()!=null)
        {
            DetailsFragment details=DetailsFragment.newInstance(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content,details).commit();
        }

        //setContentView(R.layout.frm_details);
    }
}
