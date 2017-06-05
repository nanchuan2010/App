package com.example.lzw.myapp.Maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class GeocoderActivity extends FragmentActivity {

    private static final String MAPFRAGTAG="MAPFRAGTAG";
    MyGeocoderFragment myMapFragment=null;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoder);

        if((myMapFragment=(MyGeocoderFragment)getSupportFragmentManager().findFragmentByTag(MAPFRAGTAG))==null)
        {
            myMapFragment=MyGeocoderFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container,myMapFragment,MAPFRAGTAG).commit();
        }

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
                            myMapFragment.gotoLocation(new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude()),locationName);
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

}
