package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.portpals.models.User;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button toMain = findViewById(R.id.btnLogIn);
        toMain.setTextColor(Color.parseColor("black"));
        toMain.setBackgroundColor(Color.parseColor("blue"));
        toMain.setOnClickListener(view -> {
            // sign user in with firebase auth
            signUserIn();
        });

        Button toSignUp = findViewById(R.id.btnCreateAccount);
        toSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    private void signUserIn() {
        TextView emailView = findViewById(R.id.editTextEmail);
        TextView passwordView = findViewById(R.id.editTextPassword);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        MainActivity.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    System.out.println("Failed to authenticate user");
                }
        });
    }

}