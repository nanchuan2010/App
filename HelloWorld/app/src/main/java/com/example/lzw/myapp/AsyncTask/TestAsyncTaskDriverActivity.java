package com.example.lzw.myapp.AsyncTask;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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

public class TestAsyncTaskDriverActivity extends Activity implements IReportBack, View.OnClickListener {

    public static final String tag = "AsyncTaskDriverActivity";

    private AsyncTester asyncTester = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_driver);

        asyncTester = new AsyncTester(this, this);
        Button btn = (Button) findViewById(R.id.btnAsyncTask1);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.btnAsyncTask2);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.btnAsyncTask3);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnClear);
        btn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.async_task_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        if (item.getItemId() == R.id.menu_retained) {
            intent = new Intent(this, TestAsync2TaskDriverActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.menu_progressbar) {
            intent = new Intent(this, TestProgressBarDriverActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
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
        switch (view.getId()) {
            case R.id.btnClear:
                this.emptyText();
                break;
            case R.id.btnAsyncTask1:
                asyncTester.test1();
                break;
            case R.id.btnAsyncTask2:
                asyncTester.test2();
                break;
            case R.id.btnAsyncTask3:
                asyncTester.test3();
                break;
        }
    }
}

