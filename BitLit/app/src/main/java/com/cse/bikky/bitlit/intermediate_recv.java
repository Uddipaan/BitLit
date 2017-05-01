package com.cse.bikky.bitlit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class intermediate_recv extends AppCompatActivity {

    Button btn_fnc, btn_bits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_recv);


        btn_bits = (Button)findViewById(R.id.btn_bits);
        btn_fnc = (Button) findViewById(R.id.btn_fnc);

        btn_fnc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent MyIntent = new Intent(intermediate_recv.this, fnc_recv.class);
                startActivity(MyIntent);
            }

        });

        btn_bits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent MyIntent = new Intent(intermediate_recv.this, rcv_bit.class);
                startActivity(MyIntent);
            }

        });


    }
}
