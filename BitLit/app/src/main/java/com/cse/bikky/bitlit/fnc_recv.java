package com.cse.bikky.bitlit;

import android.annotation.TargetApi;
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
import android.widget.TextView;

import java.util.Arrays;

public class fnc_recv extends AppCompatActivity implements SensorEventListener {
    private Sensor mySense;
    private SensorManager SM;
    Button btn_strt, btn_reset;
    TextView t,t2;
    public String [] bitstring = new String[8];
    int iterator = 0;
    int it = 0;
    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fnc_recv);

        btn_strt = (Button)findViewById(R.id.btn2strt);
        btn_reset = (Button)findViewById(R.id.reset);

        t = (TextView)findViewById(R.id.txt2);
        t2 = (TextView)findViewById(R.id.t2);
        for (int i = 0; i < 8; i++) {
            bitstring[i] = " ";
        }

        btn_strt.setOnClickListener(new View.OnClickListener(){

            @Override
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
                            SM.registerListener(fnc_recv.this, mySense, SensorManager.SENSOR_DELAY_NORMAL);
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
                t2.setText(" ");
            }
        });



    }

    @Override
    public void onSensorChanged(final SensorEvent event) {


        int v2 = (int) event.values[0];


        desp(v2);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




    public void disp_array(){

       //.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]+bitstring[4]+bitstring[5]+bitstring[6]+bitstring[7]);
        t.setText("Please wait ...");
    }



    public void openMsg(){
        //intent to open calculator

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openCntc(){
        //intent to open contacts
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_CONTACTS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openGlry(){
        //intent to open camera
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_GALLERY);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void fnc()
    {
        String res = Arrays.toString(bitstring);

        switch(res){
            case "[1, 0, 1, 0, 1, 0, 1, 0]":
                t.setText(" ");
                openMsg();
                break;
            case "[0, 1, 0, 1, 0, 1, 0, 1]":
                t.setText(" ");
                openGlry();
                break;
            case "[1, 1, 1, 1, 0, 0, 0, 0]":
                t.setText(" ");
                openCntc();
                break;
            default:
                t.setText(" ");
                t2.setText("Unidentified Signal Detected! \nPlease Try Again.");
                SM.unregisterListener(fnc_recv.this, mySense);
                break;
        }
    }

    public void desp(final int x)
    {



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
    else{
            fnc();
        }

    }




   /* @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        SM.unregisterListener(fnc_recv.this, mySense);

    }
*/
    public void delay() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SM.unregisterListener(fnc_recv.this, mySense);
                //t.setText("Off");
                it++;

            }
        }, 10*it);
    }

}
