package com.example.travella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class activity_home extends AppCompatActivity {

    ImageButton img, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img = findViewById(R.id.ticket);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_home.this,MainActivity.class);
                intent.putExtra("user", "LoggedIn");
                startActivity(intent);
                finish();
            }
        });

        res = findViewById(R.id.reserve);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_home.this,reservation.class);
                intent.putExtra("user", "LoggedIn");
                startActivity(intent);
                finish();
            }
        });


    }
}
