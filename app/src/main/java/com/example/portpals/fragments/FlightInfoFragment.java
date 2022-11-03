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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class FlightInfoFragment extends Fragment {

    public static FlightInfoFragment newInstance() {
        return new FlightInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("FlightInfoFrag made");
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);

        FlightInfoManager.getInstance().getFlightInfo("AC791", new RequestListener<JSONObject>() {
            @Override
            public void getResult(JSONObject obj) {
                try {
                    if (obj != null) {
                        System.out.println("Results");
                        Log.d("Result", obj.toString());
                        Map<String, Object> map = obj.toMap();
                        TextView departure = view.findViewById(R.id.departure_airport);
                        departure.setText();
                    }
                }catch (JSONException e){
                    Log.d("JSON Exception", e.getMessage());
                }
            }
        });

        TextView arrival = view.findViewById(R.id.arrival_airport);
//        arrival.setText(mViewModel.getArrivalAirport());


        return view;
    }

}