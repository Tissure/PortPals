package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.portpals.models.User;
import com.example.portpals.util.Validator;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private TextView lastNameTextView;

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView passwordConfirmTextView;
    private TextView displayNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button createAccountBtn = findViewById(R.id.btnCreateAccount);

        // attempt to sign up the user when they click the sign up button
        createAccountBtn.setOnClickListener(view -> {
            emailTextView = findViewById(R.id.editTextEmail);
            passwordTextView = findViewById(R.id.editTextPassword);
            passwordConfirmTextView = findViewById(R.id.editTextRePassword);
            displayNameTextView = findViewById(R.id.editTextDisplayName);
            firstNameTextView = findViewById(R.id.editTextFirstName);
            lastNameTextView = findViewById(R.id.editTextLastName);

            // if we have a valid sign in, add this user to firebase and send them to the main activity page
            if (checkFields()) {
                authenticateUser();
            }
        });

        // add go back to sign in button functionality
        Button toSignIn = findViewById(R.id.signInButton);
        toSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private boolean checkFields() {
        boolean isValidDisplayName = Validator.isValidDisplayName(displayNameTextView);
        boolean isValidEmail = Validator.isValidEmail(emailTextView);
        boolean isValidPassword = Validator.isValidPassword(passwordTextView);
        boolean isSamePassword = Validator.isSamePassword(passwordTextView, passwordConfirmTextView);
        return isValidDisplayName && isValidEmail && isValidPassword && isSamePassword;
    }

    private void authenticateUser() {
        // create the user data to put in the database
        User newUser = new User();
        newUser.setEmail(emailTextView.getText().toString());
        newUser.setPassword(passwordTextView.getText().toString());
        newUser.setDisplayName(displayNameTextView.getText().toString());
        newUser.setFirstName(firstNameTextView.getText().toString());
        newUser.setLastName(lastNameTextView.getText().toString());

        // add the user to the database after creating a firebase user
        MainActivity.firebaseAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword())
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = MainActivity.firebaseAuth.getCurrentUser();

                    // put the user in the Users node with an id of the uid created by the firebase user
                    String key = firebaseUser.getUid();
                    MainActivity.databaseReference.child("Users").child(key).setValue(newUser);

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    System.out.println("Failed to authenticate user");
                }
        });
    }

}