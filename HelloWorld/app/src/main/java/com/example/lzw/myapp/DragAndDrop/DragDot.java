package com.example.lzw.myapp.DragAndDrop;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/07.
 */
public class DragDot extends View implements View.OnDragListener {

    private static final int DEFAULT_RADIUS=20;
    private static final int DEFAULT_COLOR= Color.WHITE;
    private static final int SELECTED_COLOR=Color.MAGENTA;
    protected static final String DOTTAG="DragDot";
    private Paint mNormalPaint;
    private Paint mDraggingPaint;
    private int mColor=DEFAULT_COLOR;
    private int mRadius=DEFAULT_RADIUS;
    private boolean inDrag;

    public DragDot(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray myAttrs=context.obtainStyledAttributes(attrs, R.styleable.Dot);
        final int numAttrs=myAttrs.getIndexCount();
        for (int i = 0; i < numAttrs; i++) {
            int attr=myAttrs.getIndex(i);
            switch (attr)
            {
                case R.styleable.Dot_radius:
                    mRadius=myAttrs.getDimensionPixelSize(attr,DEFAULT_RADIUS);
                    break;
                case R.styleable.Dot_color:
                    mColor=myAttrs.getColor(attr,DEFAULT_COLOR);
                    break;

            }
        }
        myAttrs.recycle();

        mNormalPaint=new Paint();
        mNormalPaint.setColor(mColor);
        mNormalPaint.setAntiAlias(true);

        mDraggingPaint=new Paint();
        mDraggingPaint.setColor(SELECTED_COLOR);
        mDraggingPaint.setAntiAlias(true);

        setOnLongClickListener(lcListener);
        setOnDragListener(this);
    }

    private static View.OnLongClickListener lcListener=new View.OnLongClickListener(){
        private boolean mDragInProgress;

        @Override
        public boolean onLongClick(View v) {
            ClipData data=ClipData.newPlainText("DragData",(String)v.getTag());
            mDragInProgress=v.startDrag(data,new View.DragShadowBuilder(v),(Object)v,0);

            Log.v((String)v.getTag(),"starting drag? "+mDragInProgress);
            return true;
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size=2*mRadius+getPaddingLeft()+getPaddingRight();
        setMeasuredDimension(size,size);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        String dotTAG=(String)getTag();
        if(event.getLocalState()!=this)
        {
            Log.v(dotTAG,"This drag event is not for us");
            return false;
        }

        boolean result=true;

        int action=event.getAction();
        float x=event.getX();
        float y=event.getY();

        switch (action)
        {
            case DragEvent.ACTION_DRAG_STARTED:
                    Log.v(dotTAG,"drag started. X:"+x+",Y: "+y);
                    inDrag=true;
                    break;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.v(dotTAG,"drag proceeding...At: "+x+","+y);
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.v(dotTAG,"drag entered.At: "+x+","+y);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.v(dotTAG,"drag exited.At: "+x+","+y);
                break;
            case DragEvent.ACTION_DROP:
                Log.v(dotTAG,"drag dropped.At: "+x+","+y);
                result=false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.v(dotTAG,"drag ended.Success? "+event.getResult());
                inDrag=false;
                break;
            default:
                Log.v(dotTAG,"some other drag action: "+action);
                result=false;
                break;
        }
        return result;
    }

    @Override
    public void draw(Canvas canvas) {
        float cx=this.getWidth()/2+getLeftPaddingOffset();
        float cy=this.getHeight()/2+getTopPaddingOffset();
        Paint paint=mNormalPaint;
        if(inDrag)
        {
            paint=mDraggingPaint;
        }
        canvas.drawCircle(cx,cy,mRadius,paint);
        invalidate();
    }
}
