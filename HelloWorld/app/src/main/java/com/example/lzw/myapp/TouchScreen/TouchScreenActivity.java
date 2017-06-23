package com.example.lzw.myapp.TouchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.lzw.myapp.R;


public class TouchScreenActivity extends Activity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_boolean_button);

        RelativeLayout layout1=(RelativeLayout)findViewById(R.id.layout1);
        layout1.setOnTouchListener(this);

        Button trueBtn1=(Button) findViewById(R.id.trueBtn1);
        trueBtn1.setOnTouchListener(this);
        Button falseBtn1=(Button) findViewById(R.id.falseBtn1);
        falseBtn1.setOnTouchListener(this);

        RelativeLayout layout2=(RelativeLayout)findViewById(R.id.layout2);
        layout2.setOnTouchListener(this);

        Button trueBtn2=(Button) findViewById(R.id.trueBtn2);
        trueBtn2.setOnTouchListener(this);
        Button falseBtn2=(Button) findViewById(R.id.falseBtn2);
        falseBtn2.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String myTag=v.getTag().toString();
        Log.v(myTag,"------------------");
        Log.v(myTag,"Got view "+myTag+" in onTouch");
        Log.v(myTag,describeEvent(v,event));
        if("true".equals(myTag.substring(0,4)))
        {
            v.onTouchEvent(event);
            Log.v(myTag,"*** calling my onTouchEvent() method ***" );
            Log.v(myTag,"and I'm returning true");
            return true;
        }
        else
        {
            Log.v(myTag,"and I'm returning false");
            return false;
        }
    }

    protected static String describeEvent(View view,MotionEvent event)
    {
        StringBuilder result=new StringBuilder(300);
        result.append("Action: ").append(event.getAction()).append("\n");
        result.append("Location: ").append(event.getX()).append(" x ").append(event.getY()).append("\n");
        if (event.getX()<0 || event.getX() >view.getWidth() ||
                event.getY()<0 || event.getY()>view.getHeight())
        {
            result.append(">>> Touch has left the view <<<\n");
        }

        result.append("Edge flags: ").append(event.getEdgeFlags());
        result.append("\n");
        result.append("Pressure: ").append(event.getPressure());
        result.append("  ").append("Size: ").append(event.getSize());
        result.append("\n").append("Down time: ");
        result.append(event.getDownTime()).append("ms\n");
        result.append("Event time: ").append(event.getEventTime());
        result.append("ms").append(" Elapsed: ");
        result.append(event.getEventTime()-event.getDownTime());
        result.append(" ms \n");
        return  result.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.touch_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        switch (item.getItemId())
        {
            case R.id.menu_velocity_tracker:
                intent.setClass(this,VelocityTrackerActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_multi_touch:
                intent.setClass(this,MultiTouchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_scale_gesture:
                intent.setClass(this,ScaleGestureActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
