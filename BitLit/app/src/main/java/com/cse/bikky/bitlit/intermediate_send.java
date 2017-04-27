package com.cse.bikky.bitlit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermediate_send extends AppCompatActivity {

    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_send);

        btn1 = (Button) findViewById(R.id.snd_chr);
        btn2 = (Button) findViewById(R.id.bit);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent MyIntent = new Intent(intermediate_send.this, Send_char.class);
                startActivity(MyIntent);
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent MyIntent = new Intent(intermediate_send.this, Send_new.class);
                startActivity(MyIntent);
            }

        });
    }

}
