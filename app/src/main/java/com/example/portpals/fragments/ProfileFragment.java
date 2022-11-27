package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.portpals.EditProfileActivity;
import com.example.portpals.LandingPageActivity;
import com.example.portpals.R;
import com.example.portpals.util.FileService;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container,false);

        // set the profile picture to the image view
        ImageView profilePictureView = rootView.findViewById(R.id.profile_page_picture);
        FileService.setProfilePicture(profilePictureView);

        //rootView.findViewById(R.id.event_history_button).setOnClickListener(this);

        // log out the user and send them back to the landing page activity
        rootView.findViewById(R.id.logout_button).setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this.getActivity(), LandingPageActivity.class);
            startActivity(intent);
        });

        // send the user to the edit profile activity
        rootView.findViewById(R.id.edit_profile_button).setOnClickListener(view -> {
            Intent intent = new Intent(this.getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}