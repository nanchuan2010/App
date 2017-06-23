package com.example.lzw.myapp.TouchScreen;

import android.graphics.Matrix;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.lzw.myapp.R;


public class ScaleGestureActivity extends Activity {
    private static final String TAG = "ScaleDetector";
    private ImageView image;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1f;
    private Matrix mMatrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_scale_gesture);
        image = (ImageView) findViewById(R.id.image);
        mScaleDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG, "in onTouchEvent");
        mScaleDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            Log.v(TAG, "in onScale, scale factor=" + mScaleFactor);
            mMatrix.setScale(mScaleFactor, mScaleFactor);
            image.setImageMatrix(mMatrix);
            image.invalidate();
            return true;
        }
    }
}
