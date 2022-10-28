package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button toLinear = findViewById(R.id.btnCreateAccount);
        toLinear.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle userInfo = new Bundle();
            //TODO fill bundle
            intent.putExtra(String.valueOf(R.string.b_user_info), userInfo);
            startActivity(intent);
        });

        Button toSignIn = findViewById(R.id.signInButton);
        toSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }
}