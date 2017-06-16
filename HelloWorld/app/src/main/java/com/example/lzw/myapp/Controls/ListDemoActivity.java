package com.example.lzw.myapp.Controls;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lzw.myapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListDemoActivity extends Activity {

    private ListView listView;
    private ListView checkedListView;
    private ArrayAdapter<String> checkedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_list_activity);

        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        createSpinner(spinner);

        createListView();
        createCheckedListView();
        createGridView();

        Spinner spinnerPrompt=(Spinner)findViewById(R.id.spinnerPrompt);
        createSpinner(spinnerPrompt);
    }

    private void createSpinner(Spinner spinner)
    {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.planets,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void createListView()
    {
        listView=(ListView)findViewById(R.id.listView);
        List<String> list=getDataList();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.controls_simple_list_row,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue=(String)listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),itemValue,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createCheckedListView() {
        checkedListView=(ListView)findViewById(R.id.checkListView);
        List<String> list=getDataList();

        checkedAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,list);
        checkedListView.setAdapter(checkedAdapter);

        checkedListView.setChoiceMode(checkedListView.CHOICE_MODE_MULTIPLE);
        checkedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue=(String)checkedListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),itemValue,Toast.LENGTH_SHORT).show();
                getCheckedItems();
            }
        });
    }

    private List<String> getDataList()
    {
        String[] someColors=new String[]{"Red", "Orange", "Yellow",
                "Green", "Blue", "Indigo", "Violet", "Black", "White"};
        ArrayList<String> colorArrayList=new ArrayList<String>();
        colorArrayList.addAll(Arrays.asList(someColors));
        return colorArrayList;
    }

    private void getCheckedItems()
    {
        int count=checkedListView.getCount();
        SparseBooleanArray items=checkedListView.getCheckedItemPositions();
        if(items.size()<=0)
            return;

        String msg="";
        for (int i = 0; i < count; i++) {
            if(items.get(i))
            {
                String selectedColor=(String)checkedListView.getItemAtPosition(i);
                msg+=selectedColor+" ";
            }
        }
        msg+=" selected!";
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    /*
     *另一种获取方式
     * hasStableIds方法？？
     */
    private void  getCheckedItems2()
    {
        if(!checkedAdapter.hasStableIds())
        {
            Toast.makeText(getApplicationContext(),"Data is not stable",Toast.LENGTH_SHORT).show();
            return;
        }
        long[] items=checkedListView.getCheckedItemIds();
        String msg=null;
        for (int i = 0; i < items.length; i++) {
            String selectedColor=(String)checkedListView.getItemAtPosition(i);
            msg+=selectedColor+" ";
        }
        msg+=" selected!";
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    private void createGridView() {
        GridView gridView=(GridView) findViewById(R.id.gridView);
        List<String> list=getDataList();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
    }
}
