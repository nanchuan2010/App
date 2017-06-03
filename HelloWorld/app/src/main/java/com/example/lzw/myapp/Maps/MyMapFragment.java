package com.example.lzw.myapp.Maps;


import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback{
    private GoogleMap mMap=null;

    public static MyMapFragment newInstance(){
        MyMapFragment myMF=new MyMapFragment();
        return myMF;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        doWhenMapIsReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mMap!=null)
        {
            mMap.setMyLocationEnabled(false);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        doWhenMapIsReady();
    }

    private void doWhenMapIsReady() {
        if(mMap!=null && isResumed())
        {
            mMap.setMyLocationEnabled(true);
        }
    }
}
