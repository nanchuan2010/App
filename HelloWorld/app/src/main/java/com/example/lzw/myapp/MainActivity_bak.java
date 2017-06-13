package com.example.lzw.myapp;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity_bak extends Activity
{

    private Resources resources;
    private TextView tv=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        PreferenceManager.setDefaultValues(this,R.xml.main,false);
        resources=this.getResources();
        tv=(TextView)findViewById(R.id.text1);
        setOptionText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_prefs)
        {
            Intent intent=new Intent().setClass(this,MainPreferenceActivity.class);
            this.startActivityForResult(intent,0);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setOptionText();
    }

    private void setOptionText()
    {
        String valuesText;
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);

        String flight_option=prefs.getString(resources.getString(R.string.flight_sort_option),
                resources.getString(R.string.flight_sort_option_default_value));

        String[] optionEntries=resources.getStringArray(R.array.flight_sort_options);
        valuesText="option value is "+flight_option+" ("+optionEntries[Integer.parseInt(flight_option)]+")";

        String[] optionValues=resources.getStringArray(R.array.flight_sort_options_values);

        int index=0;
        for (;index<optionValues.length;index++)
        {
            if(optionValues[index].equals(flight_option))
            {
                break;
            }
        }

        if(index<optionValues.length)
        {
            valuesText+="\n ...or the other way to get it ("+optionEntries[index]+")";
        }

        valuesText+="\nShow Airline:"+prefs.getBoolean("show_airline_column_pref",false);
        valuesText+="\nAlert email address: "+prefs.getString("alert_email_address","");
        valuesText+="\nFavorite pizza toppings: "+prefs.getStringSet("pizza_toppings",null);

        tv.setText(valuesText);
    }
}
