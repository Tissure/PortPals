package com.example.portpals;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.portpals.models.User;
import com.example.portpals.util.FileService;
import com.example.portpals.util.Validator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EditProfileActivity extends AppCompatActivity {

    private Uri uri;

    private ImageView editProfilePicture;

    private Button editProfilePictureButton;
    private Button cancelButton;
    private Button updateProfileButton;

    private EditText displayNameEditText;
    //private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        displayNameEditText = findViewById(R.id.edit_display_name);
        //passwordEditText = findViewById(R.id.edit_password);

        editProfilePicture = findViewById(R.id.edit_profile_picture);

        editProfilePictureButton = findViewById(R.id.edit_profile_picture_button);
        cancelButton = findViewById(R.id.edit_cancel);
        updateProfileButton = findViewById(R.id.update_profile_button);

        editProfilePictureButton.setOnClickListener(view -> {
            FileService.selectImage(this);
        });

        updateProfileButton.setOnClickListener(view -> {
            // check credentials first
            boolean validDisplayName = Validator.isValidDisplayName(displayNameEditText);
            if (!validDisplayName) {
                return;
            }
            String userKey = FirebaseAuth.getInstance().getUid();

            // set new display name
            String displayName = displayNameEditText.getText().toString();
            FirebaseDatabase.getInstance().getReference().child("Users").child(userKey).child("displayName").setValue(displayName);

            // set new password
            // String password = passwordEditText.getText().toString();
            // FirebaseAuth.getInstance().getCurrentUser().updatePassword(password);

            // set new profile picture
            FileService.uploadImage(userKey, uri, this);
            // take user back to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        cancelButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        fillEverythingWithCurrentCredentials();
    }

    private void fillEverythingWithCurrentCredentials() {
        // set the image in the editProfilePicture image view to the current one initially
        FileService.setProfilePicture(editProfilePicture);

        // set the text of the display name text view to the current one initially
        String userKey = FirebaseAuth.getInstance().getUid();
        Query userQuery = FirebaseDatabase.getInstance().getReference().child("Users").child(userKey);
        userQuery.get().addOnSuccessListener(task -> {
            User userInfo = task.getValue(User.class);
            String displayName = userInfo.getDisplayName();
            displayNameEditText.setText(displayName);
        }).addOnFailureListener(task -> {
            System.out.println("Failed to obtain user information!");
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int requestCodeSuccess = 1;
        if (requestCode == requestCodeSuccess && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            editProfilePicture.setImageURI(uri);
        } else {
            System.out.println("Failed to fetch image");
        }
    }

}
