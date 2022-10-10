package com.example.portpals;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container,false);
        //Create a path to listen for a click for each button
        rootView.findViewById(R.id.btnToSignUp).setOnClickListener(this);
        rootView.findViewById(R.id.btnEventHistory).setOnClickListener(this);
        // Inflate the layout for this fragment
        return rootView;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnToSignUp:
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEventHistory:
                Intent intent2 = new Intent(getActivity(), EventHistoryActivity.class);
                startActivity(intent2);
                break;
        }

    }

}