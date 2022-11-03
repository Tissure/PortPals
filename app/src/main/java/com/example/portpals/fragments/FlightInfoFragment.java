package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portpals.R;
import com.example.portpals.util.FlightInfoManager;

public class FlightInfoFragment extends Fragment {

    private FlightInfoManager mViewModel;

    public static FlightInfoFragment newInstance() {
        return new FlightInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);

        FlightInfoManager mViewModel = new ViewModelProvider(this).get(FlightInfoManager.class);

        TextView departure = view.findViewById(R.id.departure_airport);
        departure.setText(mViewModel.getDepartureAirport());

        TextView arrival = view.findViewById(R.id.arrival_airport);
        arrival.setText(mViewModel.getArrivalAirport());


        return view;
    }

}