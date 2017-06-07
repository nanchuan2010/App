package com.example.lzw.myapp.DragAndDrop;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/07.
 */
public class Palette extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.palette, container, false);
        return v;
    }
}
