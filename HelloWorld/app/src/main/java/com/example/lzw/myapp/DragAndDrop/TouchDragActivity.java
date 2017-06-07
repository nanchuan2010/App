package com.example.lzw.myapp.DragAndDrop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.lzw.myapp.R;

/**
 * Created by Administrator on 2017/6/7.
 */

public class TouchDragActivity extends Activity {
    public LinearLayout counterLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_drag);

        counterLayout=(LinearLayout)findViewById(R.id.counters);
    }
}
