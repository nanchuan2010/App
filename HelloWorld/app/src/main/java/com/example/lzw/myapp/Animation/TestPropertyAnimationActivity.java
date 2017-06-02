package com.example.lzw.myapp.Animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import com.example.lzw.myapp.R;


/**
 * Created by LZW on 2017/06/02.
 */
public class TestPropertyAnimationActivity extends Activity {
    private static String tag="My activity";
    private TextView m_tv=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        gatherControls();
    }

    private void gatherControls() {
        m_tv=(TextView)this.findViewById(R.id.tv_id);

    }

    public void toggleAnimation(View btnView)
    {
        Button tButton=(Button)btnView;
        if(m_tv.getAlpha()!=0)
        {
            ObjectAnimator fadeOut=ObjectAnimator.ofFloat(m_tv,"alpha",0f);
            fadeOut.setDuration(5000);
            fadeOut.start();
            tButton.setText("Fade In");
        }
        else
        {
            ObjectAnimator fadeIn=ObjectAnimator.ofFloat(m_tv,"alpha",1f);
            fadeIn.setDuration(5000);
            fadeIn.start();
            tButton.setText("Fade out");
        }
    }

    public void sequentialAnimation(View bView)
    {
        m_tv.setAlpha(1f);
        ObjectAnimator fadeOut=ObjectAnimator.ofFloat(m_tv,"alpha",0f);
        ObjectAnimator fadeIn=ObjectAnimator.ofFloat(m_tv,"alpha",1f);
        AnimatorSet as=new AnimatorSet();
        as.playSequentially(fadeOut,fadeIn);
        as.setDuration(5000);
        as.start();
    }

    public void testAnimationBuilder(View v)
    {
        ObjectAnimator fadeOut=ObjectAnimator.ofFloat(m_tv,"alpha",0f);
        ObjectAnimator fadeIn=ObjectAnimator.ofFloat(m_tv,"alpha",1f);
        AnimatorSet as=new AnimatorSet();
        as.play(fadeOut).before(fadeIn);
        as.setDuration(5000);
        as.start();
    }

}
