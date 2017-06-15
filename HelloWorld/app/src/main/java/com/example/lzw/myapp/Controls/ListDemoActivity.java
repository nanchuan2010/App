package com.example.lzw.myapp.Controls;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lzw.myapp.R;

import java.util.ArrayList;
import java.util.Arrays;


public class ListDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_list_activity);

        createSpinner();
        createListView();
    }

    private void createSpinner()
    {

        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.planets,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void createListView()
    {
        ListView lv=(ListView)findViewById(R.id.listView);
        String[] someColors=new String[]{"Red", "Orange", "Yellow",
                "Green", "Blue", "Indigo", "Violet", "Black", "White"};
        ArrayList<String> colorArrayList=new ArrayList<String>();
        colorArrayList.addAll(Arrays.asList(someColors));

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,colorArrayList);
        lv.setAdapter(adapter);
    }
}
