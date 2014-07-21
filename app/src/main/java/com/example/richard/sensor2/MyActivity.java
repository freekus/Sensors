package com.example.richard.sensor2;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

// Added
import android.hardware.Sensor;
import android.content.Context;

import java.util.List;

import android.widget.Button;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


// Added java imports
import java.util.ArrayList;


public class MyActivity extends Activity {


    // Get a reference to the SensorManager class
    //variables
    private ListView listView;
    private SensorManager mSensorManager;
    //list of sensors
    private List<Sensor> deviceSensors = null;
    //list of sensors names
    private List<String> deviceSensorsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //create instance of sensor manager and get system sensor service
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //create instance of list view
        listView = ((ListView) findViewById(R.id.listView1));

        //get list of all types of sensors in you device
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            /*you can get specific sensors by selecting type in getSensorList(type you want);*/


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

        for (Sensor individualSensor : deviceSensors) {
            //get names of all the sensors in your device and add into list
            deviceSensorsList.add(individualSensor.getName());
        }
        // Bind the adapter to the list of device names
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, deviceSensorsList));
        //Make expandable listview
        //http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                // When clicked, show a toast with the TextView text
                switch (position) {
                    case 0:
                        Intent myIntent = new Intent(view.getContext(), Test1.class);
                        // Carry a bundle of data to the activity as well.
                        // Inside the sendMessage() method, use findViewById() to get the EditText element
                        // and add its text value to the intent:
                       /* EditText editText = (EditText) findViewById(R.id.edit_message);
                        String message = editText.getText().toString();
                        intent.putExtra(EXTRA_MESSAGE, message); */
                        startActivity(myIntent);
                        break;
                    case 1:
                        Intent myIntent2 = new Intent(view.getContext(), Test2.class);
                        startActivityForResult(myIntent2, 0);
                        break;
                }

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

