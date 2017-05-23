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
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Send_char extends AppCompatActivity {


    public String [] bitstring = new String[8];
    public String [] inputstring = new String[10];
    private CameraManager mCameraManager;
    private String mCameraId;
    private boolean hasFlash;
    public int m=0;
    Button btn_clc, btn_cntc, btn_gal, rst;
    public TextView t;

    // FOR CALCULATOR STRING IS 10101010
    // FOR GALLERY STRING IS 01010101
    // FOR CONTACTS STRING IS 11110000




    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_char);


        btn_clc=(Button)findViewById(R.id.btn_calc);
        btn_gal=(Button)findViewById(R.id.btn_gal);
        btn_cntc=(Button)findViewById(R.id.btn_cntc);
        rst=(Button)findViewById(R.id.rst);

        t = (TextView)findViewById(R.id.txtv);



        //camera flash configurations
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        if (!hasFlash) {

            AlertDialog alertDialog = new AlertDialog.Builder(Send_char.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("No flash found in you device!!");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }

        rst.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                for(int i = 0; i < 8 ; i++)
                {
                    bitstring[i] = " ";
                }
                m=0;

            }
        });


        btn_clc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                bitstring[0]="1";
                bitstring[1]="0";
                bitstring[2]="1";
                bitstring[3]="0";
                bitstring[4]="1";
                bitstring[5]="0";
                bitstring[6]="1";
                bitstring[7]="0";

                sendbit();


            }
        });

        btn_gal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                bitstring[0]="0";
                bitstring[1]="1";
                bitstring[2]="0";
                bitstring[3]="1";
                bitstring[4]="0";
                bitstring[5]="1";
                bitstring[6]="0";
                bitstring[7]="1";

                sendbit();


            }
        });

        btn_cntc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                bitstring[0]="1";
                bitstring[1]="1";
                bitstring[2]="1";
                bitstring[3]="1";
                bitstring[4]="0";
                bitstring[5]="0";
                bitstring[6]="0";
                bitstring[7]="0";

                sendbit();


            }
        });





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
                AlertDialog alertDialog = new AlertDialog.Builder(Send_char.this).create();
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



    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        turnOffFlash();

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
                AlertDialog alertDialog = new AlertDialog.Builder(Send_char.this).create();
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





















    public void sendbit(){

        try {
            final Handler handler = new Handler();
            for(int x=0; x<bitstring.length; x=x+1) {

                handler.postDelayed(new Runnable() {


                    public void run()
                    {

                        if(bitstring[m]=="1")
                        {
                            turnOnFlash();

                        }
                        else if(bitstring[m]=="0")
                        {
                            turnOffFlash();
                        }

                        m++;
                        int temp;
                        temp=m;
                        t.setText("Sending element "+temp+" of the string.");



                    }

                }, 1000*x);


            }
            delay();
            m=0;


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void delay() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                turnOffFlash();
                t.setText("Sending Completed Successfully!");

            }
        }, 8000);
    }



}


//GRAVEYARD
/*
        btn_snd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //first edit text length is determined
               int len=txt.getText().length();



                //then its contents are parsed individually

                String str = txt.getText().toString();
                inputstring = str.split("");

                //looped for the entire input string length

                for(String t : inputstring)
                {
                    //switch case for each input string element where bit string is initialized
                    switch(t)
                    {
                        case "A" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;

                        }
                        case "B" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "C" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "D" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "E" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "F" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "G" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "H" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "I" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "J" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "K" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "L" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "M" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "N" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "O" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "P" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "Q" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "R" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "S" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "T" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "U" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "V" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "W" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "X" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "Y" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "2" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;
                            sendbit();

                            break;
                        }
                        case "Z" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "0" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "a" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "b" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "c" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "d" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "e" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "f" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }

                        case "g" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "h" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "i" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "j" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "k" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "l" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "m" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "n" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "o" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "0" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "p" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "q" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "r" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "s" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "t" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "u" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "v" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "w" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "0" ;
                            bitstring[5] = "1" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "x" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        case "y" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "0" ;
                            bitstring[7] = "1" ;

                            sendbit();

                            break;
                        }
                        case "z" :
                        {
                            bitstring[0] = "0" ;
                            bitstring[1] = "1" ;
                            bitstring[2] = "1" ;
                            bitstring[3] = "1" ;
                            bitstring[4] = "1" ;
                            bitstring[5] = "0" ;
                            bitstring[6] = "1" ;
                            bitstring[7] = "0" ;

                            sendbit();

                            break;
                        }
                        default :
                        {
                            break;
                        }
                    }

                }





            }
        });
*/
