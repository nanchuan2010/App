package com.example.lzw.myapp.TouchScreen;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by LZW on 2017/06/06.
 */
public abstract class BooleanButton extends Button {
    public BooleanButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected boolean myValue()
    {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String myTag=this.getTag().toString();
        Log.v(myTag,"--------------------------");
        Log.v(myTag,BooleanButtonActivity.describeEvent(this,event));
        Log.v(myTag,"super onTouchEvent() returns "+super.onTouchEvent(event));
        Log.v(myTag,"and I'm returning "+myValue());
        return myValue();
    }
}
