package com.cse.bikky.bitlit;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RcvActivity extends AppCompatActivity implements SensorEventListener {

    Button button, clr;
    private TextView t;
    private Sensor mySense;
    private SensorManager SM;
    public int v2 ;
    public int x;



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not needed now
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv);

        button = (Button)findViewById(R.id.btn_strt2);
        clr = (Button)findViewById(R.id.button3);
        t = (TextView) findViewById(R.id.textView4);


        button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {


                try {
                    delay();
                    //Create Sensor manager
                    SM = (SensorManager) getSystemService(SENSOR_SERVICE);

                    //Light sensor
                    mySense = SM.getDefaultSensor(Sensor.TYPE_LIGHT);

                    //register listener
                    SM.registerListener(RcvActivity.this, mySense, 500000);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        clr.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SM.unregisterListener(RcvActivity.this, mySense);
                t.setText(" ");


            }
        });






    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        v2 = (int) event.values[0];

        if (v2 >= 1000) {
            //interpret 1
            x=1;
            display();
        } else {
            //interpret 0
            x=0;
            display();
        }
        SM.unregisterListener(RcvActivity.this, mySense);
    }

    void display()
    {
        t.setText(""+x);
    }


    public void delay() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



            }
        }, 4000);
    }
}
