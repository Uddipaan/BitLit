package com.cse.bikky.bitlit;

import android.annotation.TargetApi;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class rcv_bit extends AppCompatActivity implements SensorEventListener {


    private Sensor mySense;
    private SensorManager SM;
    Button btn_strt;
    TextView t, tst;
    public String [] bitstring = new String[8];
    int iterator = 0;
    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv_bit);

        btn_strt = (Button)findViewById(R.id.strt);

        t = (TextView)findViewById(R.id.txtvw2);
        tst = (TextView)findViewById(R.id.tst);


        for (int i = 0; i < 8; i++) {
            bitstring[i] = " ";
        }

        //start button functionality
        btn_strt.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View v) {


                try {
                    final Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {


                            public void run()
                            {
                                //Create Sensor manager
                                SM = (SensorManager) getSystemService(SENSOR_SERVICE);
                                //Light sensor
                                mySense = SM.getDefaultSensor(Sensor.TYPE_LIGHT);
                                //this requires the main class to implement sensorEventListener
                                //register listener

                                SM.registerListener(rcv_bit.this, mySense, 1000,1000);
                            }
                        }, 500);
                    SM.unregisterListener(rcv_bit.this, mySense);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });








    }




    @Override
    public void onSensorChanged(SensorEvent event) {
        int v2 = (int) event.values[0];


        if(iterator<8) {
            if (v2 >= 1000) {

                //push one to the array
                bitstring[iterator] = "1";

                iterator++;
                //display the array
                disp_array();

                } else if (v2 < 1000) {

                //push zero to the array
                bitstring[iterator] = "0";

                iterator++;

                //display the array
                disp_array();

                 }

            }
            else
            {
                tst.setText("Text recieved successfully");
                SM.unregisterListener(rcv_bit.this, mySense);
            }


    }






    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




//this will display the array in the textview

    public void disp_array(){

        t.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]+bitstring[4]+bitstring[5]+bitstring[6]+bitstring[7]);
    }



}
