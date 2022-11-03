package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portpals.R;
import com.example.portpals.util.FlightInfoManager;
import com.example.portpals.util.RequestListener;

public class FlightInfoFragment extends Fragment {

    public static FlightInfoFragment newInstance() {
        return new FlightInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("FlightInfoFrag made");
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);

        FlightInfoManager.getInstance().getFlightInfo("791", new RequestListener<String>() {
            @Override
            public void getResult(String obj) {
                System.out.println("Results");
                Log.d("Result", obj);
                TextView departure = view.findViewById(R.id.departure_airport);
                departure.setText(obj);
            }
        });

        TextView arrival = view.findViewById(R.id.arrival_airport);
//        arrival.setText(mViewModel.getArrivalAirport());


        return view;
    }

}