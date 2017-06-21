package com.example.lzw.myapp.Animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lzw.myapp.R;

public class FrameAnimationActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_frame_layout);
        this.setupButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.animation_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        switch (item.getItemId())
        {
            case R.id.menu_layout:
                intent.setClass(this,LayoutAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_view:
                intent.setClass(this,ViewAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_property:
                intent.setClass(this,PropertyAnimationActivity.class);
                startActivity(intent);
                break;
        }

        return true;

    }

    private void setupButton()
    {
        Button b=(Button)this.findViewById(R.id.startFAButtonId);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                animate();
            }
        });
    }

    private void animate() {
        ImageView imgView=(ImageView)findViewById(R.id.animationImage);
        imgView.setVisibility(ImageView.VISIBLE);
        imgView.setBackgroundResource(R.drawable.frame_animation);

        AnimationDrawable frameAnimation=(AnimationDrawable)imgView.getBackground();
        if(frameAnimation.isRunning())
        {
            frameAnimation.stop();
        }
        else
        {
            frameAnimation.stop();
            frameAnimation.start();
        }
    }

}
