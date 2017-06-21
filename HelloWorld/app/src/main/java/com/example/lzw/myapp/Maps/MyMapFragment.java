package com.example.lzw.myapp.Maps;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Administrator on 2017/6/3.
 */

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap = null;

    public static MyMapFragment newInstance() {
        MyMapFragment myMF = new MyMapFragment();
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
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                //return;
            }
            mMap.setMyLocationEnabled(false);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        doWhenMapIsReady();
    }

    private void doWhenMapIsReady() {
        if (mMap != null && isResumed()) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                //return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    public void addMarker()
    {
        LatLng disneyMagicKingdom=new LatLng(28.418971,-81.581436);
        LatLng disneyServenLagoon=new LatLng(28.410067,-81.583699);

        MarkerOptions markerOpt=new MarkerOptions()
                .draggable(false)
                .flat(false)
                .position(disneyMagicKingdom)
                .title("Magic Kingdom")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(markerOpt);

        markerOpt.position(disneyServenLagoon).title("Seven Seas Lagoon");
        mMap.addMarker(markerOpt);

        LatLngBounds latLngBox=LatLngBounds.builder().include(disneyMagicKingdom).include(disneyServenLagoon).build();
        // Move the camera to zoom in on our locations
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBox,200,200,0));
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
}
