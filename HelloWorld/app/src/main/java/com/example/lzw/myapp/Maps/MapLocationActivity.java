package com.example.lzw.myapp.Maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MapLocationActivity extends FragmentActivity {

    private static final String MAPFRAGTAG = "MAPFRAGTAG";
    private MyMapFragment myMapFrag;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_location_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if ((myMapFrag = (MyMapFragment) getSupportFragmentManager().findFragmentByTag(MAPFRAGTAG)) == null) {
            myMapFrag = MyMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, myMapFrag, MAPFRAGTAG).commit();
        }

        Button btn=(Button)findViewById(R.id.btnAddMarker);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMapFrag.addMarker();
            }
        });


        //Geocoder
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD  && !Geocoder.isPresent())
        {
            Toast.makeText(this , "Geocoder is not available on this device", Toast.LENGTH_LONG).show();
            finish();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        geocoder=new Geocoder(this);
        EditText loc=(EditText)findViewById(R.id.locationName);
        loc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_GO)
                {
                    String locationName=textView.getText().toString();
                    try
                    {
                        List<Address> addressList=geocoder.getFromLocationName(locationName,5);

                        if(addressList!=null && addressList.size()>0)
                        {
                            myMapFrag.gotoLocation(new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude()),locationName);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.maps_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        switch (item.getItemId())
        {
            case R.id.menu_location_service:
                intent.setClass(this,LocationServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_location_update:
                intent.setClass(this,LocationUpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_fused_location:
                intent.setClass(this,FusedLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_geofencing_api:
                intent.setClass(this,GeofencingApiActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
