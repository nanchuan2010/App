package com.example.lzw.myapp.Broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lzw.myapp.R;
import com.example.lzw.myapp.Utils;

/**
 * Created by LZW on 2017/05/31.
 */
public class TestBCRActivity extends Activity {
    public static final String tag="TestBCRActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testbcr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.testbcr_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        appendMenuItemText(item);
        if(item.getItemId()==R.id.menu_send_clear)
        {
            this.emptyText();
            return true;
        }
        if(item.getItemId()==R.id.menu_send_broadcast)
        {
            this.testSendBroadcast();
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
        Log.d(tag,s);
    }

    public TextView getTextView() {
        return (TextView)this.findViewById(R.id.text1);
    }

    private void testSendBroadcast()
    {
        Utils.logThreadSignature(tag);
        Intent broadcastIntent=new Intent("com.example.lzw.myapp.testbc");
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        broadcastIntent.putExtra("message","Hello world");

        this.sendBroadcast(broadcastIntent);

        Log.d(tag,"after send broadcast from main menu");
    }
}
