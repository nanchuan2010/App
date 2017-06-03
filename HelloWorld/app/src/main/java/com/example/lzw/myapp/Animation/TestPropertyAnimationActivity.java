package com.example.lzw.myapp.Animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
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

    public void sequentialAnimationXML(View bView)
    {
        AnimatorSet set=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.fadein);
        set.setTarget(m_tv);
        set.start();
    }

    public void testPropertiesHolder(View v)
    {
        float h=m_tv.getHeight();
        float w=m_tv.getWidth();
        float x=m_tv.getX();
        float y=m_tv.getY();

        m_tv.setX(w);
        m_tv.setY(h);

        PropertyValuesHolder pvhx=PropertyValuesHolder.ofFloat("x",x);
        PropertyValuesHolder pvhy=PropertyValuesHolder.ofFloat("y",y);

        ObjectAnimator oa=ObjectAnimator.ofPropertyValuesHolder(m_tv,pvhx,pvhy);
        oa.setDuration(5000);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        oa.start();
    }

    public void testViewAnimator(View v)
    {
        float h=m_tv.getHeight();
        float w=m_tv.getWidth();
        float x=m_tv.getX();
        float y=m_tv.getY();

        m_tv.setX(w); m_tv.setY(h);
        ViewPropertyAnimator vpa=m_tv.animate();
        vpa.x(x);
        vpa.y(y);

        vpa.setDuration(5000);
        vpa.setInterpolator(new AccelerateDecelerateInterpolator());

    }

    public void testTypeEvaluator(View v)
    {
        float h=m_tv.getHeight();
        float w=m_tv.getWidth();
        float x=m_tv.getX();
        float y=m_tv.getY();

        PointF startingPoint=new PointF(w,h);
        PointF endingPoint=new PointF(x,y);

        MyAnimatableView m_atv=new MyAnimatableView(m_tv);
        ObjectAnimator viewCompositeValueAnimator=ObjectAnimator.ofObject(m_atv,"point",new MyPointEvaluator(),startingPoint,endingPoint);
        viewCompositeValueAnimator.setDuration(5000);
        viewCompositeValueAnimator.start();
    }

    public void testKeyFrames(View v)
    {
        float h=m_tv.getHeight();
        float w=m_tv.getWidth();
        float x=m_tv.getX();
        float y=m_tv.getY();

        Keyframe kf0=Keyframe.ofFloat(0.2f,0.8f);
        Keyframe kf1=Keyframe.ofFloat(.5f,0.2f);
        Keyframe kf2=Keyframe.ofFloat(0.8f,0.8f);

        PropertyValuesHolder pvhAlpha=PropertyValuesHolder.ofKeyframe("alpha",kf0,kf1,kf2);
        PropertyValuesHolder pvhX=PropertyValuesHolder.ofFloat("x",w,x);

        ObjectAnimator anim=ObjectAnimator.ofPropertyValuesHolder(m_tv,pvhAlpha,pvhX);
        anim.setDuration(5000);
        anim.start();
    }
}
