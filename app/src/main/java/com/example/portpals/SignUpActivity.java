package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portpals.models.User;
import com.example.portpals.util.FileService;
import com.example.portpals.util.Validator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private TextView lastNameTextView;

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView passwordConfirmTextView;
    private TextView displayNameTextView;
    private ImageView profileImageView;
    private Button uploadProfilePictureButton;

    private Uri uri;

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

        // add functionality for uploading an image
        profileImageView = findViewById(R.id.profileImageView);
        uploadProfilePictureButton = findViewById(R.id.uploadProfilePictureButton);
        uploadProfilePictureButton.setOnClickListener(view -> {
            FileService.selectImage(this);
        });

        // add go back to sign in button functionality
        Button toSignIn = findViewById(R.id.signInButton);
        toSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int requestCodeSuccess = 1;
        if (requestCode == requestCodeSuccess && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            profileImageView.setImageURI(uri);
        } else {
            System.out.println("Failed to fetch image");
        }
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
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // get firebase user and put them into the database
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userKey = firebaseUser.getUid();

                    // attempt to put the new user in the database
                    User newUser = new User(email, displayName, firstName, lastName, userKey);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(userKey).setValue(newUser)
                            .addOnCompleteListener(this, snapshot -> {
                                if (!snapshot.isSuccessful()) {
                                    String errMsg = snapshot.getException().getMessage();
                                    Toast.makeText(this, errMsg,  Toast.LENGTH_LONG).show();
                                    FirebaseAuth.getInstance().getCurrentUser().delete();
                                }
                            });

                    // if the user successfully logged in, then upload the image asynchronously
                    if(uri != null) {
                        FileService.uploadImage(userKey, uri, this);
                    }



                    // send the user to the main page if they have logged in successfully
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to create user!", Toast.LENGTH_LONG).show();
                }
        });
    }

}