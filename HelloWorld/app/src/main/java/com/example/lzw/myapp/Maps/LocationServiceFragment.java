package com.example.lzw.myapp.Maps;


import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Administrator on 2017/6/3.
 */

public class LocationServiceFragment extends SupportMapFragment
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback
{
    private Context mContext=null;
    private GoogleMap mMap=null;
    private GoogleApiClient mClient=null;
    private LatLng mLatLng=null;

    public static LocationServiceFragment newInstance()
    {
        LocationServiceFragment myMF=new LocationServiceFragment();
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
        if(mClient==null)
            setRetainInstance(true);
            mContext=getActivity().getApplication();
            mClient=new GoogleApiClient.Builder(mContext,this,this).addApi(LocationServices.API).build();
            mClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(mContext,"Connection failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        FusedLocationProviderApi locator=LocationServices.FusedLocationApi;
        Location myLocation=locator.getLastLocation(mClient);
        if(myLocation==null)
            return;
        double lat=myLocation.getLatitude();
        double lng=myLocation.getLongitude();
        mLatLng=new LatLng(lat,lng);
        doWhenEverythingIsReady();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(mContext,"Connection suspended",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        doWhenEverythingIsReady();
    }

    private void doWhenEverythingIsReady()
    {
        if(mMap==null || mLatLng==null)
        {
            return;
        }

        MarkerOptions markerOpt=new MarkerOptions().draggable(false).flat(true)
                .position(mLatLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(markerOpt);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,15));
    }
}
