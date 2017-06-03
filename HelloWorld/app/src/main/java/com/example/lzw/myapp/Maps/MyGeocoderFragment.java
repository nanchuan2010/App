package com.example.lzw.myapp.Maps;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyGeocoderFragment extends SupportMapFragment  implements OnMapReadyCallback{
    private GoogleMap mMap=null;

    public static MyGeocoderFragment newInstance()
    {
        MyGeocoderFragment myMF=new MyGeocoderFragment();
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

    public void gotoLocation(LatLng latlng,String locString)
    {
        if(mMap==null)
            return;

        MarkerOptions markerOpt=new MarkerOptions().draggable(false).flat(false)
                .position(latlng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("You chose:").snippet(locString);
        mMap.addMarker(markerOpt);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
    }
}
