package com.example.lzw.myapp.Architecture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lzw.myapp.Utils.IntentsUtils;
import com.example.lzw.myapp.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CalculatorActivity extends Activity implements View.OnClickListener {

    private String strUri="http://www.google.com";
    private String strGeo="geo:0,0?z=4&q=business+near+city";
    private String strCall="12345678";

    private EditText number1EditText;
    private EditText number2EditText;
    private EditText uriEditText;
    private EditText geoEditText;
    private EditText callEditText;

    private SlidrConfig mSlidrConfig;
    private SlidrConfig.Builder mBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.art_calculator_activity);
        gatherControls();
        setupButtons();
        int primary=getResources().getColor(R.color.colorPrimary);
        int secondary=getResources().getColor(R.color.gray);
        mBuilder=new SlidrConfig.Builder().primaryColor(primary)
                .secondaryColor(secondary)
                .scrimColor(Color.BLACK)
                .position(SlidrPosition.LEFT)
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(5f)
                .distanceThreshold(.35f);
        mSlidrConfig=mBuilder.build();
        Slidr.attach(this,mSlidrConfig);

       // Slidr.attach(this,primary,secondary);
    }

    private void gatherControls() {
        number1EditText=(EditText)this.findViewById(R.id.editText1);
        number2EditText=(EditText)this.findViewById(R.id.editText2);
        uriEditText=(EditText)this.findViewById(R.id.edit_uri);
        geoEditText=(EditText)this.findViewById(R.id.edit_geo);
        callEditText=(EditText)this.findViewById(R.id.edit_call);

        uriEditText.setText(strUri);
        geoEditText.setText(strGeo);
        callEditText.setText(strCall);

        number2EditText.requestFocus();
    }

    private void setupButtons() {
        Button btn=(Button)this.findViewById(R.id.plusButton);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.minusButton);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.multiplyButton);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.divideButton);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.view);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.search);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.dial);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.call);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.map);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String sNum1=number1EditText.getText().toString();
        String sNum2=number2EditText.getText().toString();
        double num1=getDouble(sNum1);
        double num2=getDouble(sNum2);

        Button btn=(Button)view;
        double value=0;
        if(btn.getId()==R.id.plusButton)
        {
            value=plus(num1,num2);
        }
        else if(btn.getId()==R.id.minusButton)
        {
            value=minus(num1,num2);
        }
        else if(btn.getId()==R.id.multiplyButton)
        {
            value=multiply(num1,num2);
        }
        else if(btn.getId()==R.id.divideButton)
        {
            value=divide(num1,num2);
        }
        else if(btn.getId()==R.id.view)
        {
            String uri=uriEditText.getText().toString();
            IntentsUtils.invokeWebBrowser(this,uri);
        }
        else if(btn.getId()==R.id.search)
        {
            String uri=uriEditText.getText().toString();
            IntentsUtils.invokeWebSearch(this,uri);
        }
        else if(btn.getId()==R.id.dial)
        {
            IntentsUtils.dial(this);
        }
        else if(btn.getId()==R.id.call)
        {
            String call=callEditText.getText().toString();
            IntentsUtils.call(this,call);
        }
        else if(btn.getId()==R.id.map)
        {
            String geo=geoEditText.getText().toString();
            IntentsUtils.showMapAtLatLong(this,geo);
        }

        number1EditText.setText(Double.toString(value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.architecture_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_Some)
        {
            Intent intent=new Intent(this,SomeActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    private double plus(double n1, double n2)
    {
        return n1+n2;
    }

    private double minus(double n1,double n2)
    {
        return n1-n2;
    }

    private double multiply(double n1,double n2)
    {
        return n1*n2;
    }

    private double divide(double n1,double n2)
    {
        return n1/n2;
    }

    private double getDouble(String str)
    {
        if(validString(str))
        {
            return Double.parseDouble(str);
        }
        return 0;
    }

    private boolean invalidString(String str)
    {
        return !validString(str);
    }

    private boolean validString(String str) {
        if(str==null)
        {
            return false;
        }
        if(str.trim().equalsIgnoreCase(""))
        {
            return false;
        }

        return true;

    }


}
