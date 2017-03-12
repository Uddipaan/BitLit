package com.cse.bikky.bitlit;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RcvActivity extends AppCompatActivity implements SensorEventListener {

    Button button;
    private TextView t;
    private TextView t2;
    private Sensor mySense;
    private SensorManager SM, SM2;
    public int v2 ;

    @Override
    public void onSensorChanged(SensorEvent event) {

        v2 = (int) event.values[0];

        if (v2 >= 1000) {
            //interpret 1
            t.setText("ONE where " + v2);
        } else {
            //interpret 0
            t.setText("ZERO where " + v2);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not needed now
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv);

        //Create Sensor manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Light sensor
        mySense = SM.getDefaultSensor(Sensor.TYPE_LIGHT);

        //register listener
        SM.registerListener(this, mySense, SensorManager.SENSOR_DELAY_NORMAL);

        t = (TextView) findViewById(R.id.textView4);


    }
}
