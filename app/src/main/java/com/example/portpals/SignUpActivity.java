package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portpals.models.User;
import com.example.portpals.util.Validator;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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
            Intent intent = new Intent(this, SignInActivity.class);
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
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String displayName = displayNameTextView.getText().toString();
        String firstName = firstNameTextView.getText().toString();
        String lastName = lastNameTextView.getText().toString();

        // create a firebase user
        MainActivity.firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // get firebase user and put them into the database
                    FirebaseUser firebaseUser = MainActivity.firebaseAuth.getCurrentUser();
                    String key = firebaseUser.getUid();

                    // attempt to put the new user in the database
                    User newUser = new User(email, displayName, firstName, lastName);
                    Task putUserInDB = MainActivity.databaseReference.child("Users").child(key).setValue(newUser);
                    if (!putUserInDB.isSuccessful()) {
                        String errMsg = putUserInDB.getException().getMessage();
                        Toast.makeText(this, errMsg,  Toast.LENGTH_LONG).show();
                        MainActivity.firebaseAuth.getCurrentUser().delete();
                        return;
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to create user!", Toast.LENGTH_LONG).show();
                }
        });
    }

    private User createNewUser(FirebaseUser firebaseUser) {
        // set the firebase user display name to the one entered in the form
        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        builder.setDisplayName(displayNameTextView.getText().toString());
        firebaseUser.updateProfile(builder.build());

        // set all the information to a new user instance
        User user = new User();
        user.setFirstName(firstNameTextView.getText().toString());
        user.setLastName(lastNameTextView.getText().toString());
        user.setEmail(firebaseUser.getEmail());
        user.setDisplayName(firebaseUser.getDisplayName());
        user.setFirstName(firstNameTextView.getText().toString());
        user.setLastName(lastNameTextView.getText().toString());

        return user;
    }

}