package com.example.lzw.myapp.Controls;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.example.lzw.myapp.R;

public class LayoutActivity extends Activity {

    private ImageView one,two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_layout);

        one=(ImageView)findViewById(R.id.oneImgView);
        two=(ImageView)findViewById(R.id.twoImgView);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
            }
        });
    }

}
