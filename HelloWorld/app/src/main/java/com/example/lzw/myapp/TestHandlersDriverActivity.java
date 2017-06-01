package com.example.lzw.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by LZW on 2016/09/09.
 */
public class TestHandlersDriverActivity extends Activity {
    public static final String tag="TestDriverActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        appendMenuItemText(item);
        if(item.getItemId()==R.id.menu_clear)
        {
            this.emptyText();
            return true;
        }
        if(item.getItemId()==R.id.menu_test_thread)
        {
            this.testThread();
            return true;
        }
        if(item.getItemId()==R.id.menu_test_defered_handler)
        {
            this.testDeferedHandler();
            return true;
        }
        return true;
    }

    private TextView getTextView()
    {
        return (TextView)this.findViewById(R.id.text1);
    }

    public void appendText(String abc)
    {
        TextView tv=getTextView();
        tv.setText(tv.getText()+"\n"+abc);
    }

    private void appendMenuItemText(MenuItem menuItemm)
    {
        String title=menuItemm.getTitle().toString();
        TextView tv=getTextView();
        tv.setText(tv.getText()+"\n"+title);
    }

    private void emptyText(){
        TextView tv=getTextView();
        tv.setText("");
    }

    private DeferWorkHandler th=null;
    private void testDeferedHandler()
    {
        if(th==null)
        {
            th=new DeferWorkHandler(this);
            this.appendText("Creating a new handler");
        }

        this.appendText("Starting to do deferred work by sending message");
        th.doDeferredWork();
    }

    Handler statusBackHandler=null;
    Thread workerThread=null;
    private void testThread()
    {
        if(statusBackHandler==null)
        {
            statusBackHandler=new ReportStatusHandler(this);
            workerThread=new Thread(new WorkerThreadRunnable(statusBackHandler));
            workerThread.start();
            this.appendText("Starting to do work thread");
        }
        if(workerThread.getState()!=Thread.State.TERMINATED)
        {
            Log.d(tag,"thread is new or alive,but not terminated");
            this.appendText("is new or alive,but not terminated");
        }
        else
        {
            Log.d(tag,"thread is likely dead.starting now");
            this.appendText("is new or alive,but not terminated");
            workerThread=new Thread(new WorkerThreadRunnable(statusBackHandler));
            workerThread.start();
        }
    }
}
