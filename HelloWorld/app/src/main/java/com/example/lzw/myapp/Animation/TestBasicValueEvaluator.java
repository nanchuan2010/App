package com.example.lzw.myapp.Animation;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LZW on 2017/06/02.
 */
public class TestBasicValueEvaluator {
    private String tag="TestBasicValueEvaluator";
    public void test()
    {
        Log.d(tag,"Setting up the evaluator");
        ValueAnimator anim=ValueAnimator.ofInt(10,200);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animator)
            {
                Integer value=(Integer)animator.getAnimatedValue();
            }
        });
    }
}
