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

import java.util.Timer;

import static android.R.attr.x;


public class rcv_bit extends AppCompatActivity implements SensorEventListener {



    private Sensor mySense;
    private SensorManager SM;
    Button btn_strt, btn_reset;
    TextView t, tst;
    public String [] bitstring = new String[8];


    int it = 0;
    int iterator = 0;


    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv_bit);

        btn_strt = (Button)findViewById(R.id.strt);
        btn_reset = (Button)findViewById(R.id.reset);

        t = (TextView)findViewById(R.id.txtvw2);
        tst = (TextView)findViewById(R.id.tst);


        for (int i = 0; i < 8; i++) {
            bitstring[i] = " ";
        }

        //start button functionality
        btn_strt.setOnClickListener(new View.OnClickListener(){

           //@Override
            public void onClick(View v) {



                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                //Create Sensor manager

                SM = (SensorManager) getSystemService(SENSOR_SERVICE);
                //Light sensor
                mySense = SM.getDefaultSensor(Sensor.TYPE_LIGHT);
                //this requires the main class to implement sensorEventListener
                //register listener
              int i;
                for(i=0; i<9; i++) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {


                        public void run() {
                            delay();
                            //delay2();
                            SM.registerListener(rcv_bit.this, mySense, SensorManager.SENSOR_DELAY_NORMAL);
                            //SM.unregisterListener(rcv_bit.this, mySense);
                            delay();

                        }

                    }, 1000 * i);

                }

                    }
                }, 1000);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                for(int i = 0; i < 8 ; i++)
                {
                    bitstring[i] = " ";
                }
                iterator = 0;
                t.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]+bitstring[4]+bitstring[5]+bitstring[6]+bitstring[7]);
                tst.setText(" ");
            }
        });

    }







    public void onSensorChanged( SensorEvent event) {


        int v2 = (int) event.values[0];


        desp(v2);
        //SM.unregisterListener(rcv_bit.this, mySense);


    }






public void desp(final int x)
{
//tst.setText(""+x);


    if(iterator<8) {
        if (x >= 1000) {

            //push one to the array
            bitstring[iterator] = "1";

            iterator++;
            //display the array
            disp_array();

        } else if (x < 1000) {

            //push zero to the array
            bitstring[iterator] = "0";

            iterator++;

            //display the array
            disp_array();

        }
    }
    else
    {
        tst.setText("String Received Successfully");
    }
}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }





//this will display the array in the textview

    public void disp_array(){

        t.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]+bitstring[4]+bitstring[5]+bitstring[6]+bitstring[7]);
    }
    public void delay() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SM.unregisterListener(rcv_bit.this, mySense);
                //t.setText("Off");
            it++;

            }
        }, 10*it);
    }









   /* @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        SM.unregisterListener(rcv_bit.this, mySense);

    }*/

}









 /*
    //editable
        try {
            final Handler handler = new Handler();
            for(int x=0; x<9; x=x+1) {

                handler.postDelayed(new Runnable() {


                    public void run()
                    {

                        int v2 = (int) event.values[0];
                        //tst.setText(v2);
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
                        //delay();
                        //SM.unregisterListener(rcv_bit.this, mySense);
                        //SM.registerListener(rcv_bit.this, mySense, SensorManager.SENSOR_DELAY_NORMAL);
                    }
                }, 500*x);

                //SM.unregisterListener(rcv_bit.this, mySense);
                //SM.registerListener(rcv_bit.this, mySense, SensorManager.SENSOR_DELAY_NORMAL);
                //delay();
            }





        }
        catch (Exception e) {
            e.printStackTrace();
        }
*/

//can be reverted to the below code

/*
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

*/


