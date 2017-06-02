package com.example.lzw.myapp.Animation;

import android.util.Log;
import android.view.animation.Animation;

/**
 * Created by LZW on 2017/06/02.
 */
public class ViewAnimationListener implements Animation.AnimationListener{

    public ViewAnimationListener()
    {

    }
    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("Animation Example","onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("Animation Example","onAnimationEnd");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.d("Animation Example","onAnimationRepeat");
    }

}
