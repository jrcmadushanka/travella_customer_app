package com.example.travella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Splash extends AppCompatActivity {
    Button loginBtn, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginBtn = findViewById(R.id.button9);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash.this,activity_home.class);
                intent.putExtra("user", "LoggedIn");
                startActivity(intent);
                finish();
            }
        });

        signup = findViewById(R.id.button17);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash.this,activity_sign_up.class);
                intent.putExtra("user", "LoggedIn");
                startActivity(intent);
                finish();
            }
        });


    }
}
