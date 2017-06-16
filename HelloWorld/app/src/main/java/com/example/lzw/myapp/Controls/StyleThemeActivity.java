package com.example.lzw.myapp.Controls;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/16.
 */
public class StyleThemeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_style_theme_activity);

        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setText("This text is stored in a Spannable",TextView.BufferType.SPANNABLE);

        EditText et=(EditText)findViewById(R.id.et);
        et.setText("Styling the content of an EditText dynamically");
        Spannable spn=(Spannable)et.getText();
        spn.setSpan(new BackgroundColorSpan(Color.RED),0,7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spn.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
