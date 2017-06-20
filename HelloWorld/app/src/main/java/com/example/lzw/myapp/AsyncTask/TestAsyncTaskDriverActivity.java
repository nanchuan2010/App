package com.example.lzw.myapp.AsyncTask;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

public class TestAsyncTaskDriverActivity extends Activity implements IReportBack {

    public static final String tag = "AsyncTaskDriverActivity";

    private AsyncTester asyncTester = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_driver);

        asyncTester = new AsyncTester(this, this);
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
        appendMenuItemText(item);
        if (item.getItemId() == R.id.menu_clear) {
            this.emptyText();
            return true;
        }
        if (item.getItemId() == R.id.menu_test_async1) {
            asyncTester.test1();
            return true;
        }
        if (item.getItemId() == R.id.menu_test_async2) {
            asyncTester.test2();
            return true;
        }
        if (item.getItemId() == R.id.menu_test_async3) {
            asyncTester.test3();
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

}
