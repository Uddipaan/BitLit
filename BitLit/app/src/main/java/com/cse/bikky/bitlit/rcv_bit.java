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
    Button btn_strt, btn_reset, btn_bnch;
    TextView t, tst;
    public String [] bitstring = new String[8];
    public int [] hamm = new int[12];

    int v2;
    int it = 0;
    int bikky=0;
    int iterator = 0;
    int TH=1000;


    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv_bit);

        btn_strt = (Button)findViewById(R.id.strt);
        btn_reset = (Button)findViewById(R.id.reset);
        btn_bnch = (Button)findViewById(R.id.bnch);

        t = (TextView)findViewById(R.id.txtvw2);
        tst = (TextView)findViewById(R.id.tst);


        for (int i = 0; i < 8; i++) {
            bitstring[i] = " ";
        }
        for (int i = 0; i < 12; i++) {
            hamm[i] = 0;
        }


        //start button functionality
        btn_strt.setOnClickListener(new View.OnClickListener(){

           //@Override
            public void onClick(View v) {



                bikky=1;
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
                for(i=0; i<13; i++) {
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



        btn_bnch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                tst.setText("Computing Threshold...");
                bikky=0;
                SM = (SensorManager) getSystemService(SENSOR_SERVICE);
                //Light sensor
                mySense = SM.getDefaultSensor(Sensor.TYPE_LIGHT);
                SM.registerListener(rcv_bit.this, mySense, SensorManager.SENSOR_DELAY_NORMAL);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SM.unregisterListener(rcv_bit.this, mySense);
                        tst.setText("The threshold is :"+v2);
                        t.setText("Ready");
                        TH=v2+10;

                    }
                },5000);
            }
        });

    }







    public void onSensorChanged( SensorEvent event) {


        v2 = (int) event.values[0];


        if(bikky==1) {
            desp2(v2);
            //SM.unregisterListener(rcv_bit.this, mySense);
        }

    }






public void desp(final int x) {
//tst.setText(""+x);

    //without hamming

    if (iterator < 8) {
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
    } else {
        tst.setText("String Received Successfully");
    }
}




public void desp2(final int x)
    {
    //with hamming

    if(iterator<12) {
    t.setText("Please Wait...");
        if (x >= TH) {

            //push one to the array
            hamm[iterator] = 1;
            tst.setText("Recieved Bit:"+(iterator+1));
            iterator++;

            //disp_array3();

        } else if (x < TH) {

            //push zero to the array
            hamm[iterator] = 0;
            tst.setText("Recieved Bit:"+(iterator+1));
            iterator++;

            //disp_array3();
        }


    }
    else
    {
        hammingrcv();

    }

}


    public void hammingrcv()
    {
        int p1 = hamm[0];
        int p2 = hamm[1];
        int d3 = hamm[2];
        int p4 = hamm[3];
        int d5 = hamm[4];
        int d6 = hamm[5];
        int d7 = hamm[6];
        int p8 = hamm[7];
        int d9 = hamm[8];
        int d10 = hamm[9];
        int d11 = hamm[10];
        int d12 = hamm[11];

        int tp1,tp2,tp4,tp8;

        tp1 = d3^d5^d7^d9^d11;
        tp2 = d3^d6^d7^d10^d11;
        tp4 = d5^d6^d7^d12;
        tp8 = d9^d10^d11^d12;

        if(tp1==p1 && tp2==p2 && tp4==p4 && tp8==p8)
        {
            tst.setText("String Received Successfully");
            disp_array2();
        }
        else
        {
            tst.setText("Error in String");
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }





//this will display the array in the textview

    public void disp_array(){

        t.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]+bitstring[4]+bitstring[5]+bitstring[6]+bitstring[7]);
    }

    public void disp_array2(){

        t.setText(""+hamm[2]+hamm[4]+hamm[5]+hamm[6]+hamm[8]+hamm[9]+hamm[10]+hamm[11]);
    }

    /*public void disp_array3(){


        //t.setText(""+m);
        t.setText(""+hamm[0]+hamm[1]+hamm[2]+hamm[3]+hamm[4]+hamm[5]+hamm[6]+hamm[7]+hamm[8]+hamm[9]+hamm[10]+hamm[11]);
    }*/


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


