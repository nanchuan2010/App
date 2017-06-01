package com.example.lzw.myapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by LZW on 2016/08/20.
 */
public class DetailsActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(ExampleActivity.TAG,"in DetailsActivity onCreate");
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

        //setContentView(R.layout.details);
    }
}
