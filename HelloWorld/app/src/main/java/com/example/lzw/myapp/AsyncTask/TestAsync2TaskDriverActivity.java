package com.example.lzw.myapp.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/05/31.
 */
public class TestAsync2TaskDriverActivity extends MonitoredActivityWithADOSupport implements IReportBack,View.OnClickListener {
    public static final String tag = "AsyncTaskDriverActivity";

    AsyncTesterRADO asyncTester = null;
    AsyncTesterFragment asyncTesterFragment = null;

    public TestAsync2TaskDriverActivity() {
        super(tag);
    }

    AsyncTesterRADO getAsyncTester() {
        return (AsyncTesterRADO) getRootRADO();
    }

    @Override
    protected RetainedADO onCreateRADO() {
        return new AsyncTesterRADO(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asyncTester = getAsyncTester();
        asyncTesterFragment = AsyncTesterFragment.establishRetainedAsyncTesterFragment(this);
        setContentView(R.layout.async_task_driver);

        Button btn=(Button)findViewById(R.id.btnAsyncTask1);
        btn.setText("Fragment Progress");
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnAsyncTask2);
        btn.setVisibility(View.GONE);

        btn=(Button)findViewById(R.id.btnAsyncTask3);
        btn.setVisibility(View.GONE);
    }


    private void appendMenuItemText(MenuItem menuItem) {
        String title = menuItem.getTitle().toString();
        TextView tv = getTextView();
        tv.setText(tv.getText() + "\n" + title);
    }

    private void emptyText() {
        TextView tv = getTextView();
        tv.setText("");
    }

    private void appendText(String s) {
        TextView tv = getTextView();
        tv.setText(tv.getText() + "\n" + s);
        Log.d(tag, s);
    }

    @Override
    public void reportBack(String tag, String message) {
        this.appendText(tag + ":" + message);
        Log.d(tag, message);
    }

    @Override
    public void reportTransient(String tag, String message) {
        String s = tag + ":" + message;
        Toast mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        mToast.show();
        reportBack(tag, message);
        Log.d(tag, message);
    }

    public TextView getTextView() {
        return (TextView) this.findViewById(R.id.text1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnAsyncTask1:
                asyncTester.testFragmentProgressDialog();
                break;
        }
    }
}
