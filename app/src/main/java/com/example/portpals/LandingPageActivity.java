package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        Button signUP = findViewById(R.id.btnLoginSignUp);
        signUP.setTextColor(Color.parseColor("black"));
        signUP.setBackgroundColor(Color.parseColor("white"));
        signUP.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

        Button toMain = findViewById(R.id.btnEnterFlightNum);
        toMain.setTextColor(Color.parseColor("white"));
        toMain.setBackgroundColor(Color.parseColor("blue"));
        toMain.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}