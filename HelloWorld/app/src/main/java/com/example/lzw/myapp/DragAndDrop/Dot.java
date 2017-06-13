package com.example.lzw.myapp.DragAndDrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.myapp.MainActivity_bak;

/**
 * Created by Administrator on 2017/6/7.
 */

public class Dot extends View {
    private static final String TAG="TouchDrag";
    private float left=0;
    private float top=0;
    private float radius=20;
    private float offsetX;
    private float offsetY;
    private Paint myPaint;
    private Context myContext;

    public Dot(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        myContext=context;
        myPaint=new Paint();
        myPaint.setColor(Color.WHITE);
        myPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action= event.getAction();
        float eventX=event.getX();
        float eventY=event.getY();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                if(!(left-20<eventX && eventX<left+radius*2+20 && top-20 <eventY && eventY<top+radius*2+20))
                    return false;

                offsetX=eventX-left;
                offsetY=eventY-top;
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        left=eventX-offsetX;
                        top=eventY-offsetY;
                        if(action==MotionEvent.ACTION_UP)
                        {
                            checkDrop(eventX,eventY);
                        }
                        break;
        }

        invalidate();
        return true;
    }

    private void checkDrop(float x,float y)
    {
        Log.v(TAG,"checking drop target for "+x+","+y);
        int viewCount=((TouchDragActivity)myContext).counterLayout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            View view=((TouchDragActivity)myContext).counterLayout.getChildAt(i);
            if(view.getClass()== TextView.class)
            {
                Log.v(TAG,"Is the drop to the right of "+(view.getLeft()-20));
                Log.v(TAG," and vertically between "+(view.getTop()-20)+" and "+(view.getBottom()+20)+"?");
                if(x>view.getLeft()-20 && view.getTop()-20<y && y<view.getBottom()+20)
                {
                    Log.v(TAG," Yes.Yes it is.");
                    int count=Integer.parseInt(((TextView)view).getText().toString());
                    ((TextView)view).setText(String.valueOf(++count));
                    left=top=0;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(left+radius,top+radius,radius,myPaint);
    }
}
