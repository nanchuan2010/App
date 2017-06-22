package com.example.lzw.myapp.Maps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Administrator on 2017/6/4.
 */

public class FusedLocationActivity extends FragmentActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        DialogInterface.OnCancelListener {
    private static final String TAG = "FusedLocationApiUpdates";

    private GoogleApiClient client = null;

    private static final int PLAY_SERVICES_RECOVERY_REQUEST = 777;

    private static final int LOCATION_SETTINGS_REQUEST = 888;

    private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 999;

    private enum FIX {NO_FAIL, PLAY_SERVICES, LOCATION_SETTINGS, CONNECTION}

    private FIX lastFix = FIX.NO_FAIL;
    private TextView modeStr = null;
    private FusedLocationProviderApi locator = LocationServices.FusedLocationApi;
    private LocationRequest locReq = null;
    private TextView priorityStr = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_fused_location);
        locReq = LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY).setInterval(10000).setFastestInterval(5000);
        client = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();
        modeStr = (TextView) findViewById(R.id.mode);
        priorityStr = (TextView) findViewById(R.id.priority);

        Log.v(TAG, "Activity,client are created");
    }

    private void tryToConnect() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google Play services is available.");
            if (!isLocationEnabled(this)) {
                if (lastFix == FIX.LOCATION_SETTINGS) {
                    Log.e(TAG, "Location settings didn't work");
                    finish();
                } else {
                    Toast.makeText(this, "Location Services are off.Can't work without them.Please turn them on.", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "Location Services need to be on.Launching the Settings screen");
                    startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), LOCATION_SETTINGS_REQUEST);
                    lastFix = FIX.LOCATION_SETTINGS;
                }
            } else {
                client.connect();
                Log.v(TAG, "Connecting to GoogleApiClient...");
            }
        } else if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
            if (lastFix == FIX.PLAY_SERVICES) {
                Log.e(TAG, "Recovery doesn't seem to work");
                finish();
            } else {
                Log.d(TAG, "Google Play services may be available.Asking user for help");
                GooglePlayServicesUtil.showErrorDialogFragment(resultCode, this, PLAY_SERVICES_RECOVERY_REQUEST, this);
                lastFix = FIX.PLAY_SERVICES;
            }
        } else {
            Log.e(TAG, "Google Play Services is/are not available.No point in countinuing");
            finish();
        }
    }

    private boolean isLocationEnabled(Context context) {
        int locationMode = Settings.Secure.LOCATION_MODE_OFF;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
                String MODES[] = {"OFF", "SENSORS_ONLY", "BATTERY_SAVING", "HIGH ACCURACY"};
                Log.v(TAG, "locationMode is " + MODES[locationMode]);
                modeStr.setText("Current mode is " + MODES[locationMode]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v(TAG, "Connected!");
        lastFix = FIX.NO_FAIL;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        locator.requestLocationUpdates(client, locReq, this);
        Log.v(TAG, "Requesting location updates (onConnected)...");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            Log.i(TAG, "Connection failed,trying to resolve it...");
            if (lastFix == FIX.CONNECTION) {
                Log.e(TAG, "Connection retry didn't work");
                finish();
            }
            try {
                lastFix = FIX.CONNECTION;
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "Could not resolve connection failure");
                e.printStackTrace();
                finish();
            }
        } else {
            Log.e(TAG, "Connection failed,no resolutions available, " + GooglePlayServicesUtil.getErrorString(connectionResult.getErrorCode()));
            Toast.makeText(this, "Connection failed.Cannot continue", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLAY_SERVICES_RECOVERY_REQUEST:
                Log.v(TAG, "Got a result for Play Services Recovery");
                break;
            case LOCATION_SETTINGS_REQUEST:
                Log.v(TAG, "Got a result for Location Settings");
                break;
            case CONNECTION_FAILURE_RESOLUTION_REQUEST:
                Log.v(TAG, "Got a result for connection failure");
                break;
        }

        Log.v(TAG, "resultCode was " + resultCode);
        Log.v(TAG, "End of onActivityResult");
    }


    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection suspended", Toast.LENGTH_LONG).show();
        Log.v(TAG, "Connection suspended");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resuming activity");
        tryToConnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Pausing activity,removing location updates,disconnecting...");
        if (client.isConnected()) {
            locator.removeLocationUpdates(client, this);
            client.disconnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Destroying activity,GoogleApiClient...");
        client = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(TAG, "Got a location change,showing Toast msg");
        Toast.makeText(this, "New location latitude [" + location.getLatitude() + "] longitude [" + location.getLongitude() + "] accuracy [" + location.getAccuracy() + "] meters]", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCancel(DialogInterface dialogInterface) {
        Log.d(TAG, "User does not want to fix the problem.Bailing out");
        finish();
    }



    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.btnNoPower:
                locReq.setPriority(LocationRequest.PRIORITY_NO_POWER);
                priorityStr.setText("Current priority is NO_POWER");
                break;
            case R.id.btnLowPower:
                locReq.setPriority(LocationRequest.PRIORITY_LOW_POWER);
                priorityStr.setText("Current priority is LOW_POWER");
                break;
            case R.id.btnBalanced:
                locReq.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                priorityStr.setText("Current priority is BALANCED");
                break;
            case R.id.btnHighAccuracy:
                locReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                priorityStr.setText("Current priority is HIGH_ACCURACY");
                break;
        }
        if (client.isConnected()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

            locator.requestLocationUpdates(client, locReq, this);
        }

        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), LOCATION_SETTINGS_REQUEST);
    }
}
