package com.example.lzw.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lzw.myapp.AlarmManager.AlarmManagerDriverActivity;
import com.example.lzw.myapp.Animation.FrameAnimationActivity;
import com.example.lzw.myapp.Architecture.CalculatorActivity;
import com.example.lzw.myapp.AsyncTask.TestAsyncTaskDriverActivity;
import com.example.lzw.myapp.Broadcast.BroadcastActivity;
import com.example.lzw.myapp.Controls.ControlsDemoActivity;
import com.example.lzw.myapp.Dialogs.DialogActivity;
import com.example.lzw.myapp.Fragments.FragmentActivity;
import com.example.lzw.myapp.Handlers.HandlersDriverActivity;
import com.example.lzw.myapp.Maps.WhereAmIActivity;
import com.example.lzw.myapp.Preferences.MainPreferenceActivity;
import com.example.lzw.myapp.Services.GetAndPostActivity;

import java.util.LinkedHashMap;

public class MainActivity extends Activity {

    private ListView listView;
    LinkedHashMap<String,Activity> map=new LinkedHashMap<String,Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
    }

    private void setupListView() {

        map.put("Architecture", new CalculatorActivity());
        map.put("Controls", new ControlsDemoActivity());
        map.put("Fragments",new FragmentActivity());
        map.put("Dialogs",new DialogActivity());
        map.put("Preferences",new MainPreferenceActivity());
        map.put("Handlers",new HandlersDriverActivity());
        map.put("Services",new GetAndPostActivity());
        map.put("Async Task",new TestAsyncTaskDriverActivity());
        map.put("Broadcast",new BroadcastActivity());
        map.put("Alarm Manager",new AlarmManagerDriverActivity());
        map.put("Animation",new FrameAnimationActivity());
        map.put("Maps",new WhereAmIActivity());

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
