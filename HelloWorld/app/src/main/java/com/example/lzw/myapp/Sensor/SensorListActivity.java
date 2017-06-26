package com.example.lzw.myapp.Sensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lzw.myapp.R;

import java.util.HashMap;
import java.util.List;


public class SensorListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors_list);

        TextView text=(TextView)findViewById(R.id.text);
        SensorManager mgr=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors=mgr.getSensorList(Sensor.TYPE_ALL);
        StringBuilder message=new StringBuilder(2048);
        message.append("The sensors on the device are:\n");

        for (Sensor sensor :sensors)
        {
            message.append(sensor.getName()+"\n");
            message.append(" Type: "+sensorTypes.get(sensor.getType())+"\n");
            message.append(" Vendor: "+sensor.getVendor()+"\n");
            message.append(" Version: "+sensor.getVersion()+"\n");
            try
            {
                message.append(" Min Delay: "+sensor.getMinDelay()+"\n");
            }
            catch (NoSuchMethodError e)
            {
            }
            try
            {
                message.append(" FIFO Max Event Count: "+sensor.getFifoMaxEventCount());
            }
            catch (NoSuchMethodError e)
            {}
            message.append(" Resolution :"+sensor.getResolution()+"\n");
            message.append(" Max Range :"+sensor.getMaximumRange()+"\n");
            message.append(" Power :"+sensor.getPower()+" mA\n");
        }

        text.setText(message);
    }

    private HashMap<Integer,String> sensorTypes=new HashMap<Integer, String>();
    {
        sensorTypes.put(Sensor.TYPE_ACCELEROMETER,"TYPE_ACCELEROMETER");
        sensorTypes.put(Sensor.TYPE_AMBIENT_TEMPERATURE,"TYPE_AMBIENT_TEMPERATURE");
        sensorTypes.put(Sensor.TYPE_GAME_ROTATION_VECTOR,"TYPE_GAME_ROTATION_VECTOR");
        sensorTypes.put(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,"TYPE_GEOMAGNETIC_ROTATION_VECTOR");
        sensorTypes.put(Sensor.TYPE_GRAVITY,"TYPE_GRAVITY");
        sensorTypes.put(Sensor.TYPE_GYROSCOPE,"TYPE_GYROSCOPE");
        sensorTypes.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED,"TYPE_GYROSCOPE_UNCALIBRATED");
        sensorTypes.put(Sensor.TYPE_LIGHT,"TYPE_LIGHT");
        sensorTypes.put(Sensor.TYPE_LINEAR_ACCELERATION,"TYPE_LINEAR_ACCELERATION");
        sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD,"TYPE_MAGNETIC_FIELD");
        sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,"TYPE_MAGNETIC_FIELD_UNCALIBRATED");
        sensorTypes.put(Sensor.TYPE_ORIENTATION,"TYPE_ORIENTATION");
        sensorTypes.put(Sensor.TYPE_PRESSURE,"TYPE_PRESSURE");
        sensorTypes.put(Sensor.TYPE_PROXIMITY,"TYPE_PROXIMITY");
        sensorTypes.put(Sensor.TYPE_RELATIVE_HUMIDITY,"TYPE_RELATIVE_HUMIDITY");
        sensorTypes.put(Sensor.TYPE_ROTATION_VECTOR,"TYPE_ROTATION_VECTOR");
        sensorTypes.put(Sensor.TYPE_SIGNIFICANT_MOTION,"TYPE_SIGNIFICANT_MOTION");
        sensorTypes.put(Sensor.TYPE_STEP_COUNTER,"TYPE_STEP_COUNTER");
        sensorTypes.put(Sensor.TYPE_STEP_DETECTOR,"TYPE_STEP_DETECTOR");
        sensorTypes.put(Sensor.TYPE_TEMPERATURE,"TYPE_TEMPERATURE");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.sensors_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        switch (item.getItemId())
        {
            case R.id.menu_light:
                intent.setClass(this,LightSensorActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_gravity:
                intent.setClass(this,GravitySensorActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
