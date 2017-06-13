package com.example.lzw.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
    }

    private void setupListView() {
        String[] listItems=new String[]{"Architecture","AlarmManager","Animation","Loader","Contacts"};
        ArrayAdapter<String> listItemAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        ListView lv=(ListView)this.findViewById(R.id.list_title);
        lv.setAdapter(listItemAdapter);
    }

}
