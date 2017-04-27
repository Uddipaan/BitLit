package com.cse.bikky.bitlit;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
//import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
//import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendActivity extends AppCompatActivity {

    //private boolean isFlashOn;
    private boolean hasFlash;
    private EditText t;
    private CameraManager mCameraManager;
    private String mCameraId;
    private Button button1;

    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {







        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        mCameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        //check if flash is present in phone
        if(!hasFlash)
        {

            AlertDialog alertDialog = new AlertDialog.Builder(SendActivity.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("No flash found in you device!!");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialogInterface,int which){
                    finish();
                }
            });
            alertDialog.show();
        }
        button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v){
            try
            {
                LightSend();
            }

            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        });


    }
    public void LightSend()
    {
        t = (EditText)findViewById(R.id.BinaryInput);
        String x = t.getText().toString();
        int bin = Integer.valueOf(x);

        if( bin ==  1)
        {
            //Light on
            turnOnFlash();
            //delay
            delay();


        }
        else
        {
            //No light

        }
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
                AlertDialog alertDialog = new AlertDialog.Builder(SendActivity.this).create();
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
                AlertDialog alertDialog = new AlertDialog.Builder(SendActivity.this).create();
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


    public void delay()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                turnOffFlash();
            }
        }, 2000);
    }
}
