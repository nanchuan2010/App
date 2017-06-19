package com.example.lzw.myapp.Preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2016/09/05.
 */
public class PreferenceBasicFrag extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_nested_screen);
    }
}
