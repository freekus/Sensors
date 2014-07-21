package com.example.richard.sensor2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Test2 extends Activity implements SensorEventListener {

    // Get a reference to the SensorManager class
    //variables
    private ListView listView;
    private SensorManager mSensorManager;
    //list of sensors
    private List<Sensor> deviceSensors = null;
    //list of sensors names
    private List<String> deviceSensorsList = new ArrayList<String>();

    private Sensor accelerometer;
    private TextView accelTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        //create instance of sensor manager and get system sensor service
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //create instance of list view
        listView = ((ListView) findViewById(R.id.listView1));

        //get list of all types of sensors in you device
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            /*you can get specific sensors by selecting type in getSensorList(type you want);*/

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        accelTextView = (TextView)findViewById(R.id.accelTextView);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {

    }

    public void onSensorChanged (SensorEvent event)
    {
        // NOTE: The accelerometer axis' change when device is reoriented
        // IOW, X and Y become reversed when in landscape orientation
        accelTextView.setText("X: "+ event.values[0] + "\nY: " +
                event.values[1] + "\nZ:" + event.values[2]);


    }

}
