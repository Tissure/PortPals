package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.portpals.R;
import com.example.portpals.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container,false);
        //Create a path to listen for a click for each button
        rootView.findViewById(R.id.profile_page_picture).setOnClickListener(this);
        rootView.findViewById(R.id.event_history_button).setOnClickListener(this);
        rootView.findViewById(R.id.logout_button).setOnClickListener(this);
        rootView.findViewById(R.id.edit_profile_button).setOnClickListener(this);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_page_picture:
                setProfilePicture((ImageView) view);
                break;
        }
    }

    private void setProfilePicture(ImageView profilePictureView) {
        FirebaseUser loggedInUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DataSnapshot userSnapShot : task.getResult().getChildren()) {
                    if (userSnapShot.getKey().equals(loggedInUser.getUid())) {
                        User user = userSnapShot.getValue(User.class);
                        String imageKey = user.getProfilePictureKey();

                        // set the profile picture if it exists
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        if (user != null) {
                            StorageReference profilePictureRef = storageReference.child("images/" + imageKey);
                            if (profilePictureRef != null) {
                                profilePictureRef.getDownloadUrl().addOnCompleteListener(task2 -> {
                                    if (task2.isSuccessful()) {
                                        Picasso.get().load(task2.getResult()).into(profilePictureView);
                                    } else {
                                        System.out.println("Failed to get profile picture and put it on the event");
                                    }
                                });
                            }
                        }
                        break;
                    }
                }
            } else {
                System.out.println("Failed to obtain profile information from Firestore");
                System.out.println(task.getException().getMessage());
            }
        });
    }

}