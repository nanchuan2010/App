package com.example.lzw.myapp.TouchScreen;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by LZW on 2017/06/06.
 */
public class TrueButton extends BooleanButton {
    public TrueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected boolean myValue()
    {
        return true;
    }
}
