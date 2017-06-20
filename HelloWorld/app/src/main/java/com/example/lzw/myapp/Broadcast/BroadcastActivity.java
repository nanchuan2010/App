package com.example.lzw.myapp.Broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lzw.myapp.R;
import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/05/31.
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {
    public static final String tag = "BroadcastActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_testbcr_activity);

        Button btn = (Button) findViewById(R.id.btnBroadcast);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.btnClear);
        btn.setOnClickListener(this);
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

    public TextView getTextView() {
        return (TextView) this.findViewById(R.id.text1);
    }

    private void testSendBroadcast() {
        Utils.logThreadSignature(tag);
        Intent broadcastIntent = new Intent("com.example.lzw.myapp.testbc");
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        broadcastIntent.putExtra("message", "Hello world");

        this.sendBroadcast(broadcastIntent);

        Log.d(tag, "after send broadcast from main menu");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBroadcast:
                this.testSendBroadcast();
                break;
            case R.id.btnClear:
                this.emptyText();
                break;
        }
    }
}
