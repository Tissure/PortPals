package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.portpals.R;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Fragment fg = new FlightInfoFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.flightInfo, fg).commit();

        FrameLayout fl = view.findViewById(R.id.weatherInfo);

        TextView text=new TextView(getActivity());
        text.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        text.setText("test");
        fl.addView(text);
        return view;
    }
}