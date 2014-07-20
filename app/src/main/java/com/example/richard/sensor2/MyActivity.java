package com.example.richard.sensor2;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

// Added
import android.hardware.Sensor;
import android.content.Context;
import java.util.List;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


// Added java imports
import java.util.ArrayList;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        onClickListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    private void sensorMethod() {
        // Get a reference to the SensorManager class
        //variables
         ListView listView;
         SensorManager mSensorManager;
        //list of sensors
         List<Sensor> deviceSensors = null;
        //list of sensors names
        List<String> deviceSensorsList = new ArrayList<String>();


            //create instance of list view
            listView = ((ListView) findViewById(R.id.listView1));
            //create instance of sensor manager and get system sensor service
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            //get list of all types of sensors in you device
            deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            /*you can get specific sensors by selecting type in getSensorList(type you want);*/
            for(Sensor individualSensor: deviceSensors){
                //get names of all the sensors in your device and add into list
                deviceSensorsList.add(individualSensor.getName());
            }
            // Bind the adapter to the list of device names
            listView.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, deviceSensorsList));
        //Make expandable listview
        //http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });


    }

    public void onClickListener() {
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sensorMethod();
            }
        });
    }

}

