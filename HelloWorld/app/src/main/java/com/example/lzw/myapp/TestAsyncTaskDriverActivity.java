package com.example.lzw.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestAsyncTaskDriverActivity extends Activity implements IReportBack {

    public static final String tag="AsyncTaskDriverActivity";

    private AsyncTester asyncTester=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        asyncTester=new AsyncTester(this,this);
    }

    public void doAsyncClick(View target)
    {
        asyncTester.respondToMenuItem();
    }

    private void appendText(String s)
    {
        TextView tv=getTextView();
        tv.setText(tv.getText()+"\n"+s);
        Log.d(tag,s);
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
        return (TextView)this.findViewById(R.id.text1);
    }

}
