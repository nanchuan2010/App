package com.example.lzw.myapp.AlarmManager;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

public class AlarmManagerDriverActivity extends Activity implements IReportBack,View.OnClickListener{

    public static final String tag="AlarmManagerDriverActivity";

    private CancelRepeatingAlarm alarmTester=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_driver);
        alarmTester=new CancelRepeatingAlarm(this,this);

        Button btn=(Button)findViewById(R.id.btnAsyncTask1);
        btn.setText("Send Once Alarm");
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnAsyncTask2);
        btn.setText("Send Repeat Alarm");
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnAsyncTask3);
        btn.setText("Send Cancel Alarm");
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnClear);
        btn.setOnClickListener(this);
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
        return (TextView)this.findViewById(R.id.text1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnAsyncTask1:
                alarmTester.sendAlarmOnce();
                break;
            case R.id.btnAsyncTask2:
                alarmTester.sendRepeatingAlarm();
                break;
            case R.id.btnAsyncTask3:
                alarmTester.cancelRepeatingAlarm();
                break;
            case R.id.btnClear:
                this.emptyText();
                break;
        }
    }
}
