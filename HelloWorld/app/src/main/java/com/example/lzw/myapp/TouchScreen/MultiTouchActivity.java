package com.example.lzw.myapp.TouchScreen;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lzw.myapp.R;


public class MultiTouchActivity extends Activity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);

        RelativeLayout layout1=(RelativeLayout)findViewById(R.id.layout1);
        layout1.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String myTag=v.getTag().toString();
        Log.v(myTag,"--------------------");
        Log.v(myTag,"Got view "+myTag+" in onTouch");
        Log.v(myTag,describeEvent(event));
        logAction(event);
        if("true".equals(myTag.substring(0,4)))
        {
            return true;
        }
        return false;
    }

    protected static String describeEvent(MotionEvent event)
    {
        StringBuilder result=new StringBuilder(500);
        result.append("Action: ").append(event.getAction()).append("\n");
        int numPointers=event.getPointerCount();
        result.append("Number of pointers: ");
        result.append(numPointers).append("\n");
        int ptrIdx=0;
        while (ptrIdx<numPointers)
        {
            int ptrId=event.getPointerId(ptrIdx);
            result.append("Pointer Index: ").append(ptrIdx);
            result.append(", Pointer Id: ").append(ptrId).append("\n");
            result.append(" Location: ").append(event.getX(ptrIdx));
            result.append(" x ").append(event.getY(ptrIdx)).append("\n");
            result.append(" Pressure: ");
            result.append(event.getPressure(ptrIdx));
            result.append(" Size: ").append(event.getSize(ptrIdx));
            result.append("\n");

            ptrIdx++;
        }

        result.append("Down time: ").append(event.getDownTime());
        result.append("ms\n").append("Event time: ");
        result.append(event.getEventTime()).append("ms");
        result.append(" Elapsed: ");
        result.append(event.getEventTime()-event.getDownTime());
        result.append(" ms\n");

        return result.toString();
    }

    private void logAction(MotionEvent event)
    {
        int action=event.getActionMasked();
        int ptrIndex=event.getActionIndex();
        int ptrId=event.getPointerId(ptrIndex);
        if(action==5|| action==6)
        {
            action=action-5;
        }

        Log.v("Action","Pointer index: "+ptrIndex);
        Log.v("Action","Pointer Id: "+ptrId);
        Log.v("Action","True action value:"+action);
    }

}
