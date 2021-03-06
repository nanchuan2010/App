package com.example.lzw.myapp.Sensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lzw.myapp.R;


public class LightSensorActivity extends Activity implements SensorEventListener{

    private SensorManager mgr;
    private Sensor light;
    private TextView text;
    private StringBuilder msg=new StringBuilder(2048);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_list);

        mgr=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        light=mgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        text=(TextView)findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        mgr.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this,light);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        msg.insert(0,sensor.getName()+" accuracy changed: "+accuracy+(accuracy==1?" (LOW)":(accuracy==2?" (MED)":" (HIGH)"))+"\n");
        text.setText(msg);
        text.invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        msg.insert(0,"Got a sensor event: "+sensorEvent.values[0]+" SI lux units\n");
        text.setText(msg);
        text.invalidate();
    }

}
