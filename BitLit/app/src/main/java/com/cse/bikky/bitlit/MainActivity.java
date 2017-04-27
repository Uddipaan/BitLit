package com.cse.bikky.bitlit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button_send);
        button2 = (Button) findViewById(R.id.button_recieve);
        button3 = (Button) findViewById(R.id.button_exit);
        button4 = (Button) findViewById(R.id.send);
        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SendActivity.class);
                startActivity(myIntent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        RcvActivity.class);
                startActivity(myIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent MyIntent = new Intent(MainActivity.this, intermediate_send.class);
                startActivity(MyIntent);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                finish();
                System.exit(0);

            }
        });
    }
}

