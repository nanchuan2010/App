package com.example.lzw.myapp.Provider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public abstract class MonitoredDebugActivity extends BaseActivity implements IReportBack {

    protected abstract boolean onMenuItemSelected(MenuItem item);

    private static String tag=null;
    private boolean m_retainState=false;

    protected MonitoredDebugActivity(int inMenuId, String intag) {
        super(R.layout.activity_debug_layout,R.menu.test_book_persistence_menu, intag);
        tag=intag;
    }

    public void retainState(){
        m_retainState=true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        appendMenuItemText(item);
        if(item.getItemId()==R.id.menu_da_clear)
        {
            this.emptyText();
            return true;
        }

        return onMenuItemSelected(item);
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
        Log.d(tag,s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String st=savedInstanceState.getString("debugViewText");
        if(st==null)
            return;
        TextView tv=getTextView();
        tv.setText(st);
        Log.d(tag,"Restored state");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(this.m_retainState==false)
            return;

        TextView tv=getTextView();
        String t=tv.getText().toString();
        outState.putString("debugViewText",t);
        Log.d(tag,"Saved state");
    }

    @Override
    public void reportBack(String tag, String message) {
        this.appendText(tag+":"+message);
        Log.d(tag,message);
    }



    public TextView getTextView() {
        return (TextView)this.findViewById(R.id.text1);
    }
}

