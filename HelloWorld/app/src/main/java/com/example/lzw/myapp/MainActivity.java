package com.example.lzw.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lzw.myapp.Architecture.CalculatorActivity;

import java.util.HashMap;

public class MainActivity extends Activity {

    private ListView listView;
    HashMap<String,Activity> map=new HashMap<String,Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
    }

    private void setupListView() {
        map.put("Architecture", new CalculatorActivity());
        map.put("Architecture2", new CalculatorActivity());

        //String[] listItems=new String[]{"Architecture","AlarmManager","Animation","Loader","Contacts"};
        String[] listItems=new String[map.size()];
        map.keySet().toArray(listItems);
        ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        listView=(ListView)this.findViewById(R.id.list_title);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemValue=(String)listView.getItemAtPosition(i);
                Activity activity=map.get(itemValue);
                Intent intent=new Intent(getApplicationContext(),activity.getClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("some-key","some-value");
                startActivity(new Intent(intent));
            }
        });
    }

}
