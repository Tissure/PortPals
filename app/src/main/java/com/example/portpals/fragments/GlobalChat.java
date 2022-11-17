package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.portpals.R;
import com.example.portpals.Chat;


public class GlobalChat extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_chat, container, false);
        //Create a path to listen for a click for each button
        rootView.findViewById(R.id.sendBtn).setOnClickListener(this);

        // Inflate the layout for this fragment
        return rootView;

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "this", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Chat.class);
                startActivity(intent);
    }
}


