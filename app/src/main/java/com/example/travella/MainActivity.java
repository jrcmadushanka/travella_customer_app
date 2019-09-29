package com.example.travella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ticketsBtn, mytokens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticketsBtn = findViewById(R.id.ticketBtn);
        mytokens = findViewById(R.id.pTicketsBtn);

        ticketsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, Route.class);
                startActivity(intent);
            }
        });

        mytokens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, Route.class);
                startActivity(intent);
            }
        });
    }
}
