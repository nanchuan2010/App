package com.example.lzw.myapp.Animation;

import android.animation.TypeEvaluator;
import android.graphics.PointF;


/**
 * Created by Administrator on 2017/6/2.
 */

public class MyPointEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF startPoint=(PointF)startValue;
        PointF endPoint=(PointF)endValue;
        return new PointF(startPoint.x+fraction*(endPoint.x-startPoint.x),
                            startPoint.y+fraction*(endPoint.y-startPoint.y));
    }
}
