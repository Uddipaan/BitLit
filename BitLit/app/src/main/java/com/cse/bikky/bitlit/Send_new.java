package com.cse.bikky.bitlit;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Send_new extends AppCompatActivity {


    public TextView t, test;
    private Button btn_1, btn_0, btn_clr, btn_send;
    public String [] bitstring = new String[4];
    public int n = -1;
    private CameraManager mCameraManager;
    private String mCameraId;
    private boolean hasFlash;


    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new);
        t = (TextView) findViewById(R.id.textView7);
        btn_1 = (Button) findViewById(R.id.one_btn);
        btn_0 = (Button) findViewById(R.id.zero_btn);
        btn_clr = (Button) findViewById(R.id.clr_btn);
        btn_send = (Button) findViewById(R.id.Send);

        test = (TextView) findViewById(R.id.textView6); //test

        //camera flash configurations
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        if (!hasFlash) {

            AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("No flash found in you device!!");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }

        //initializing the bit string to be null
        for (int i = 0; i < 4; i++) {
            bitstring[i] = " ";
        }


        btn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick( View v) {
                try {
                    n = n + 1;
                    if(n<4) {

                        one_input();
                    }
                    else {
                        AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Not more than 4 bits");

                        alertDialog.show();
                    }
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick( View v) {
                try {
                    n = n + 1;
                    if(n<4) {

                        zero_input();
                    }
                    else
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Not more than 4 bits");

                        alertDialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        btn_clr.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick( View v) {
                try {
                    clr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    lightsend();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });







    }


    public void one_input()
    {
        bitstring[n]= "1";

        disp_array();


    }

    public void zero_input()
    {
        bitstring[n]= "0";

        disp_array();


    }

    public void disp_array()
    {

        t.setText(bitstring[0]+bitstring[1]+bitstring[2]+bitstring[3]);
    }

    public void clr()
    {
        for(int i = 0; i < 4 ; i++)
        {
            bitstring[i] = " ";
        }
        t.setText(" "+bitstring[0]+" "+bitstring[1]+" "+bitstring[2]+" "+bitstring[3]);
        n=-1;
    }


    public void lightsend()
    {
        int o,z;
        o=0;
        z=0;
        int i=0;
        while( i<bitstring.length)
        {

            if(bitstring[i] == "1")
            {
                turnOnFlash();
                //delay();
                //t.setText("i1 = "+i);
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        turnOffFlash();
                        handler.postDelayed(this,2000);
                    }
                });
                o=o+1;

            }
            else if(bitstring[i] == "0")
            {
                turnOffFlash();
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        turnOffFlash();
                        handler.postDelayed(this,2000);
                    }
                });
                //t.setText("i0 = "+i);
                z=z+1;
            }
            i++;
        }
        test.setText("No. of ones: "+o+"No. of zeros: "+z);    //test
    }

    public void delay()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                turnOffFlash();

            }
        }, 1000);
    }
    public void delay2()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
                alertDialog.setTitle("Delay");
                alertDialog.setMessage("Delay working");

                alertDialog.show();

            }
        }, 2000);
    }

    public void turnOnFlash()
    {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                mCameraManager.setTorchMode(mCameraId,true);
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Lollipop karne nai hua!!");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface,int which){
                        finish();
                    }
                });
                alertDialog.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void turnOffFlash()
    {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                mCameraManager.setTorchMode(mCameraId,false);
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(Send_new.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Lollipop karne nai hua!!");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface,int which){
                        finish();
                    }
                });
                alertDialog.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
