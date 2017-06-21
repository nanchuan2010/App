package com.example.lzw.myapp.Maps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lzw.myapp.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Administrator on 2017/6/4.
 */

public class LocationUpdateActivity extends FragmentActivity {
    LocationManager locMgr = null;
    LocationListener locListener = null;
    MyMapFragment myMapFrag=null;
    private static final String MAPFRAGTAG="LOCATIONUPDATETAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_where_am_i);

        Button btn=(Button)findViewById(R.id.btnAddMarker);
        btn.setVisibility(View.GONE);

        EditText et=(EditText)findViewById(R.id.locationName);
        et.setVisibility(View.GONE);

        if((myMapFrag=(MyMapFragment) getSupportFragmentManager().findFragmentByTag(MAPFRAGTAG))==null)
        {
            myMapFrag=MyMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container,myMapFrag,MAPFRAGTAG).commit();
        }

        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    Toast.makeText(getBaseContext(), "New location latitude [" + location.getLatitude()
                            + "] longitude [" + location.getLongitude() + "]", Toast.LENGTH_SHORT).show();
                    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                    myMapFrag.gotoLocation(latLng,"My Position");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

            return;
        }
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locMgr.removeUpdates(locListener);
    }
}
