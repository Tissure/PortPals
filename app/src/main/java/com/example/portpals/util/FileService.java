package com.example.portpals.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.portpals.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class FileService {

    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageReference = storage.getReference();

    /**
     * @author Clayton Hunter
     *
     * Uploads an image to firebase under images/(key).
     * @param key used to identify the image
     * @param uri for the image
     * @param activity used for displaying toasts and getting the context
     */
    public static void uploadImage(String key, Uri uri, Activity activity) {
        StorageReference profilePictureRef = storageReference.child("images/" + key);

        profilePictureRef.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                Snackbar.make(activity.findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show();
            })
            .addOnFailureListener(e -> {
                Toast.makeText(activity, "Failed to upload a profile picture", Toast.LENGTH_LONG).show();
            });
    }

    /**
     * @author Clayton Hunter
     *
     * Makes an implicit intent to obtain an image from the android phone or google drive.
     * The result is sent back to the activity in the onActivityResult method, which you will
     * need to handle there
     */
    public static void selectImage(Activity activity) {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // this method works for the intent, but is deprecated, try find a better one if possible
        activity.startActivityForResult(intent, 1);
    }

    /**
     * Obtains the logged in users' profile picture and puts it into the view specified.
     * @param profilePictureView is where the image is placed
     */
    public static void setProfilePicture(ImageView profilePictureView) {
        System.out.println("beginning profile picture fetch...");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userKey = firebaseUser.getUid();
        System.out.println(userKey);

        databaseReference.child("Users").child(userKey).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                System.out.println(task.getResult());
                User userInfo = task.getResult().getValue(User.class);
                if (userInfo == null) {
                    System.out.println("Failed to convert user info into a user class");
                    return;
                }
                String imageKey = userInfo.getProfilePictureKey();

                // set the profile picture if it exists
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference profilePictureRef = storageReference.child("images/" + imageKey);
                if (profilePictureRef != null) {
                    profilePictureRef.getDownloadUrl().addOnCompleteListener(task2 -> {
                        if (task2.isSuccessful()) {
                            System.out.println("Setting profile picture");
                            Picasso.get().load(task2.getResult()).into(profilePictureView);
                        } else {
                            System.out.println("Failed to get profile picture and put it on the event");
                        }
                    });
                } else {
                    System.out.println("failed to obtain profile picture reference");
                }
            }
        });
    }

}
