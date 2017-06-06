package com.example.lzw.myapp.TouchScreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/06.
 */
public class VelocityTrackerActivity extends Activity {
    private static final String TAG="VelocityTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boolean_button);
    }

    private VelocityTracker vTracker=null;

    public boolean onTouchEvent(MotionEvent event)
    {
        int action=event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                if(vTracker==null)
                {
                    vTracker=VelocityTracker.obtain();
                }
                else
                {
                    vTracker.clear();
                }

                vTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                vTracker.addMovement(event);
                vTracker.computeCurrentVelocity(1000);
                Log.v(TAG,"X velocity is "+vTracker.getXVelocity()+" pixels per second");
                Log.v(TAG,"Y velocity is "+vTracker.getYVelocity()+" pixels per second");
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.v(TAG,"Final X velocity is "+vTracker.getXVelocity()+" pixels per second");
                Log.v(TAG,"Final Y velocity is "+vTracker.getYVelocity()+" pixels per second");
                vTracker.recycle();
                vTracker=null;
                break;
        }
        return true;
    }
}
