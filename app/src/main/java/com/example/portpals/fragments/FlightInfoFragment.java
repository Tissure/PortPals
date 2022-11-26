package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portpals.R;
import com.example.portpals.models.Airport;
import com.example.portpals.models.flight.FlightInfo;
import com.example.portpals.util.AirportsInfoManager;
import com.example.portpals.util.FlightInfoManager;
import com.example.portpals.util.RequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FlightInfoFragment extends Fragment {

    public static FlightInfoFragment newInstance() {
        return new FlightInfoFragment();
    }
    public static String iata;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("FlightInfoFrag made");
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);

        //TODO DISABLE
        try {
            JSONObject obj = new JSONObject(loadJSONFromFile());
            JSONArray data = obj.getJSONArray("data");
            FlightInfo flight;
            flight = new Gson().fromJson(data.getJSONObject(0).toString(), FlightInfo.class);
            AirportsInfoManager.getInstance(flight.getDeparture().getIata(), flight.getArrival().getIata());
            populateFlightInfo(view, flight);
            AirportsInfoManager.getInstance().getDeparture(flight.getDeparture().getIata()).observe(getActivity(), departure ->{
                popAirport(view, departure, R.string.popDeparture);
            });
            AirportsInfoManager.getInstance().getArrival(flight.getArrival().getIata()).observe(getActivity(), arrival ->{
                popAirport(view, arrival, R.string.popArrival);
            });
       } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button flightNumberButton = getView().findViewById(R.id.flightNumberButton);
        flightNumberButton.setOnClickListener(view -> {
            EditText flightNumberText = getView().findViewById(R.id.flightNumberText);
            String flightNumber = flightNumberText.getText().toString().trim();

            //String flightNo = "AC793";
            // Set the updated flight info based on the flight number entered by the user
            FlightInfoManager fm = FlightInfoManager.getInstance();
            fm.getFlight(flightNumber).observe(getActivity(), flightInfo -> {
                AirportsInfoManager.getInstance(flightInfo.getDeparture().getIata(), flightInfo.getArrival().getIata());
                populateFlightInfo(view, flightInfo);
                AirportsInfoManager.getInstance().getDeparture(flightInfo.getDeparture().getIata()).observe(getActivity(), departure -> {
                    popAirport(view, departure, R.string.popDeparture);
                });
                AirportsInfoManager.getInstance().getArrival(flightInfo.getArrival().getIata()).observe(getActivity(), arrival -> {
                    popAirport(view, arrival, R.string.popArrival);
                });
            });
        });
    }

    private void populateFlightInfo(View view, FlightInfo flight) {
        TextView departureTextView = view.findViewById(R.id.departure_airport);
        departureTextView.setText(flight.getDeparture().getIata());
        TextView arrivalTextView = view.findViewById(R.id.arrival_airport);
        arrivalTextView.setText(flight.getArrival().getIata());

        setIata(flight.getDeparture().getIata());
        TextView departureTimeTextView = view.findViewById(R.id.departure_time);
        String departureTime = DateFormat.format("HH:mm", parseDate(flight.getDeparture().getScheduled())).toString();
        departureTimeTextView.setText(departureTime);

        TextView arrivalTimeTextView = view.findViewById(R.id.arrival_time);
        String arrivalTime = DateFormat.format("HH:mm", parseDate(flight.getArrival().getScheduled())).toString();
        arrivalTimeTextView.setText(arrivalTime);

        Log.d("Populate: ", departureTextView.getText().toString());
        Log.d("Populate: ", arrivalTimeTextView.getText().toString());
    }

    private void popAirport(View view, Airport airport, int type) {
        if (type == R.string.popDeparture) {
            TextView city = view.findViewById(R.id.departure_city);
            city.setText(airport.getCity());
            TextView timeZone = view.findViewById(R.id.departure_timezone);
            timeZone.setText(String.valueOf(airport.getTimeZone()));
        } else if (type == R.string.popArrival) {
            TextView city = view.findViewById(R.id.arrival_city);
            city.setText(airport.getCity());
            TextView timeZone = view.findViewById(R.id.arrival_timezone);
            timeZone.setText(String.valueOf(airport.getTimeZone()));
        }
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(date);
        } catch (ParseException e) {
            Log.d("Parse", e.getMessage());
        }
        return null;
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

    public static String getIata() {
        return iata;
    }

    public static void setIata(String iata) {
        FlightInfoFragment.iata = iata;
    }
}