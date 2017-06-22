package com.example.lzw.myapp.Maps;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.lzw.myapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2017/06/05.
 */
public class GeofencingApiActivity extends FragmentActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<Status> {

    private static final String TAG = "GeofencingApiDemo";
    private GoogleApiClient mClient = null;
    private GeofencingApi mFencer = LocationServices.GeofencingApi;
    private List<Geofence> mGeofences = new ArrayList<Geofence>();
    private PendingIntent pIntent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_fused_location);

        final float radius = 0.5f * 1609.0f;

        Geofence.Builder gb = new Geofence.Builder();
        Geofence home = gb.setCircularRegion(28.993818, -81.383816, radius).setTransitionTypes(
                Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .setExpirationDuration(12 * 60 * 60 * 1000)
                .setLoiteringDelay(300000)
                .setRequestId("home")
                .setNotificationResponsiveness(5000)
                .build();
        mGeofences.add(home);

        Geofence work = gb.setCircularRegion(28.36631, -81.52120, radius).setRequestId("work").build();
        mGeofences.add(work);

        Intent intent = new Intent(this, ReceiveTransitionsIntentService.class);

        pIntent = PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();

        Log.v(TAG, "Activity,client are created");
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Connection failed,no resolutions available," + GooglePlayServicesUtil.getErrorString(connectionResult.getErrorCode()));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v(TAG, "Setting up geofences (onConnected)...");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        PendingResult<Status> pResult = mFencer.addGeofences(mClient, mGeofences, pIntent);
        pResult.setResultCallback(this);
    }

    @Override
    public void onResult(@NonNull Status status) {
        Log.v(TAG, "Got a result from addGeofences (" + status.getStatusCode() + "):" + status.getStatus().getStatusMessage());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v(TAG, "Connection suspended");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resuming activity");
        tryToConnect();
    }

    private void tryToConnect() {
        if (mClient.isConnected())
            return;

        mClient.connect();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Destroying activity,GoogleApiClient...");
        if (mClient.isConnected()) {
            Log.v(TAG, "Removing geofences,disconnecting...");
            mFencer.removeGeofences(mClient, pIntent);
            mClient.disconnect();
        }

        mClient = null;
    }
}
