package com.example.lzw.myapp.Provider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Administrator on 2017/6/9.
 */

public class BaseActivity extends MonitoredActivity {
    private int menuid=0;
    private int layoutid=0;

    protected BaseActivity(int defaultLayoutId,int defaultMenuId,String intag) {
        super(intag);
        layoutid=defaultLayoutId;
        menuid=defaultMenuId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menuid>0)
        {
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(menuid,menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected void gotoActivity(Class<? extends Activity> activityClass)
    {
        Intent intent=new Intent(this,activityClass);
        startActivity(intent);
    }
}
