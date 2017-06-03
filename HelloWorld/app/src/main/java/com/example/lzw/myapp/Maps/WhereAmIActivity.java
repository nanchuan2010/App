package com.example.lzw.myapp.Maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.lzw.myapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WhereAmIActivity extends FragmentActivity  {
    private static final String MAPFRAGTAG="MAPFRAGTAG";
    private MyMapFragment myMapFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_i);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if((myMapFrag = (MyMapFragment) getSupportFragmentManager().findFragmentByTag(MAPFRAGTAG))==null)
        {
            myMapFrag=MyMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container,myMapFrag,MAPFRAGTAG).commit();
        }
    }


}
