package com.example.lzw.myapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LZW on 2017/05/31.
 */
public class TestProgressBarDriverActivity extends MonitoredActivityWithADOSupport implements IReportBack {
    public static final String tag="TestProgressBarDriverActivity";
    AsyncTesterRADO asyncTester=null;

    AsyncTesterFragment asyncTesterFragment=null;

    public TestProgressBarDriverActivity()
    {
        super(tag);
    }

    AsyncTesterRADO getAsyncTester()
    {
        return (AsyncTesterRADO)getRootRADO();
    }

    @Override
    protected RetainedADO onCreateRADO() {
        return new AsyncTesterRADO(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asyncTester=getAsyncTester();
        asyncTesterFragment=AsyncTesterFragment.establishRetainedAsyncTesterFragment(this);
        ProgressBar pb=(ProgressBar)findViewById(R.id.tpb_progressBar1);
        pb.setSaveEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.tpb_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        appendMenuItemText(item);
        if(item.getItemId()==R.id.tpb_menu_test1)
        {
            asyncTesterFragment.testFragmentProgressDialog();
            return true;
        }

        return true;
    }

    private void appendMenuItemText(MenuItem menuItem)
    {
        String title=menuItem.getTitle().toString();
        TextView tv=getTextView();
        tv.setText(tv.getText()+"\n"+title);
    }

    private void emptyText()
    {
        TextView tv=getTextView();
        tv.setText("");
    }

    private void appendText(String s)
    {
        TextView tv=getTextView();
        tv.setText(tv.getText()+"\n"+s);
        //Log.d(tag,s);
    }

    @Override
    public void reportBack(String tag, String message) {
        this.appendText(tag+":"+message);
        Log.d(tag,message);
    }

    @Override
    public void reportTransient(String tag, String message) {
        String s=tag+":"+message;
        Toast mToast= Toast.makeText(this,s,Toast.LENGTH_SHORT);
        mToast.show();
        reportBack(tag,message);
        Log.d(tag,message);
    }

    public TextView getTextView() {
        return (TextView)this.findViewById(R.id.tpb_text1);
    }
}
