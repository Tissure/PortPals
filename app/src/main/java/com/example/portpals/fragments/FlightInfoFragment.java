package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portpals.R;
import com.example.portpals.models.flight.FlightInfo;
import com.example.portpals.util.FlightInfoManager;
import com.example.portpals.util.RequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

        try {
            JSONObject obj = new JSONObject(loadJSONFromFile());
            JSONArray data = obj.getJSONArray("data");
            FlightInfo flight;
            flight = new Gson().fromJson(data.getJSONObject(0).toString(), FlightInfo.class);
            populateFlightInfo(view, flight);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //TODO RENABLE
//        String flightNo = "AC793";
//        FlightInfoManager fm = FlightInfoManager.getInstance();
//        fm.getFlight(flightNo).observe(getActivity(), flightInfo -> {
//            populateFlightInfo(view, flightInfo);
//        });
        return view;
    }

    private void populateFlightInfo(View view, FlightInfo flight) {
        TextView departure = view.findViewById(R.id.departure_airport);
        departure.setText(flight.getDeparture().getAirport());
    }

    private String loadJSONFromFile() {
        String json = null;
        try {
            InputStream is = getActivity().getResources().openRawResource(R.raw.flightdump);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}