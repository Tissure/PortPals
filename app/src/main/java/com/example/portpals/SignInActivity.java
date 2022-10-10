package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button toMain = findViewById(R.id.btnLogIn);
        toMain.setTextColor(Color.parseColor("black"));
        toMain.setBackgroundColor(Color.parseColor("blue"));
        toMain.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button toSignUp = findViewById(R.id.btnCreateAccount);
        toSignUp.setTextColor(Color.parseColor("black"));
        toSignUp.setBackgroundColor(Color.parseColor("blue"));
        toSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}