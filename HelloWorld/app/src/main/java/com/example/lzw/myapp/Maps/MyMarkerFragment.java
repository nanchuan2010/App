package com.example.lzw.myapp.Maps;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyMarkerFragment extends SupportMapFragment implements OnMapReadyCallback {
    public static MyMarkerFragment newInstance()
    {
        MyMarkerFragment myMF=new MyMarkerFragment();
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
    public void onMapReady(GoogleMap googleMap) {
        LatLng disneyMagicKingdom=new LatLng(28.418971,-81.581436);
        LatLng disneyServenLagoon=new LatLng(28.410067,-81.583699);

        MarkerOptions markerOpt=new MarkerOptions().draggable(false).flat(false)
                .position(disneyMagicKingdom)
                .title("Magic Kingdom")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        googleMap.addMarker(markerOpt);

        markerOpt.position(disneyServenLagoon).title("Seven Seas Lagoon");
        googleMap.addMarker(markerOpt);

        LatLngBounds latLngBox=LatLngBounds.builder().include(disneyMagicKingdom).include(disneyServenLagoon).build();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBox,200,200,0));
    }
}

