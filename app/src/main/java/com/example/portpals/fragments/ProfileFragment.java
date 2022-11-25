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
        setProfilePicture(rootView.findViewById(R.id.profile_page_picture));

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