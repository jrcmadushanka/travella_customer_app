package com.example.travella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Route extends AppCompatActivity {

    Button getTicketBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        getTicketBtn = findViewById(R.id.getTicketBtn);

        getTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Route.this, TokenGenerator.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
