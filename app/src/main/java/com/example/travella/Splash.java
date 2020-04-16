package com.example.travella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    Button loginBtn;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginBtn = findViewById(R.id.login_btn);
        userName = findViewById(R.id.userNicInput);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((Globals)getApplication()).setUserID(userName.getText().toString());
                String userId =  ((Globals)getApplication()).getUserID();
                if (! userId.equals("")){
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    intent.putExtra("user", "LoggedIn");
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(Splash.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
