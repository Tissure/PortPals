package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portpals.models.User;
import com.example.portpals.util.Validator;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageReference = storage.getReference();

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
            selectImage();
        });

        // add go back to sign in button functionality
        Button toSignIn = findViewById(R.id.signInButton);
        toSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void selectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // this method works for the intent, but is deprecated, try find a better one if possible
        startActivityForResult(intent, 1);
    }

    private void uploadImage(String key) {
        StorageReference profilePictureRef = storageReference.child("images/" + key);

        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setTag("Uploading Image...");
        progressBar.setVisibility(View.VISIBLE);

        profilePictureRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            Snackbar.make(findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show();
                    progressBar.invalidate();
            })
            .addOnFailureListener(e -> {
                Toast.makeText(getApplicationContext(), "Failed to upload a profile picture", Toast.LENGTH_LONG).show();
                progressBar.invalidate();
            })
            .addOnProgressListener(snapshot -> {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressBar.setProgress((int)progressPercent);
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
                    uploadImage(userKey);

                    // send the user to the main page if they have logged in successfully
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to create user!", Toast.LENGTH_LONG).show();
                }
        });
    }

}