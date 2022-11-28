package com.example.portpals.fragments;

import android.graphics.Color;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.models.Airport;
import com.example.portpals.models.flight.FlightInfo;
import com.example.portpals.util.AirportsInfoManager;
import com.example.portpals.util.FlightInfoManager;
import com.example.portpals.util.RequestListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class FlightInfoFragment extends Fragment {

    TextView weatherView;
    //    http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=61ecd5de90be5cf0137e8f56761899b2
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "61ecd5de90be5cf0137e8f56761899b2";
    DecimalFormat df = new DecimalFormat("#.##");
//    private String city;
    DatabaseReference db = MainActivity.databaseReference;

    public static FlightInfoFragment newInstance() {
        return new FlightInfoFragment();
    }
    public static String iata;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("FlightInfoFrag made");
        View view = inflater.inflate(R.layout.fragment_flight_info, container, false);


        weatherView = view.findViewById(R.id.weatherTextView);

//
//
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromFile());
//            JSONArray data = obj.getJSONArray("data");
//            FlightInfo flight;
//            flight = new Gson().fromJson(data.getJSONObject(0).toString(), FlightInfo.class);
//            AirportsInfoManager.getInstance(flight.getDeparture().getIata(), flight.getArrival().getIata());
//            populateFlightInfo(view, flight);
//            AirportsInfoManager.getInstance().getDeparture(flight.getDeparture().getIata()).observe(getActivity(), departure ->{
//                popAirport(view, departure, R.string.popDeparture);
//            });
//            AirportsInfoManager.getInstance().getArrival(flight.getArrival().getIata()).observe(getActivity(), arrival ->{
//                popAirport(view, arrival, R.string.popArrival);
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
        Button flightNumberButton = view.findViewById(R.id.flightNumberButton);
        flightNumberButton.setOnClickListener(view1 -> {
            EditText flightNumberText = view.findViewById(R.id.flightNumberText);
            String flightNo = flightNumberText.getText().toString().trim();

            FlightInfoManager fm = FlightInfoManager.getInstance();

            fm.getFlight(flightNo).observe(getActivity(), flightInfo -> {
                AirportsInfoManager.getInstance(flightInfo.getDeparture().getIata(), flightInfo.getArrival().getIata());
                populateFlightInfo(view, flightInfo);
                AirportsInfoManager.getInstance().getDeparture(flightInfo.getDeparture().getIata()).observe(getActivity(), departure -> {
                    popAirport(view, departure, R.string.popDeparture);
                });
                AirportsInfoManager.getInstance().getArrival(flightInfo.getArrival().getIata()).observe(getActivity(), arrival -> {
                    popAirport(view, arrival, R.string.popArrival);
                });
            });
            getWeatherDetails();
        });

        return view;
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

    public void getWeatherDetails() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String city = snapshot.child("Airports").child(iata).child("city").getValue(String.class);
            String tempUrl = url + "?q=" + city + "&APPID=" + appid;
            Toast.makeText(getActivity(), city, Toast.LENGTH_LONG).show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
//                        weatherView.setTextColor(Color.rgb(68,134,199));
                        output += "Current weather of " + cityName + " (" + countryName + ")"
                                + "\n Temp: " + df.format(temp) + " °C"
                                + "\n Feels Like: " + df.format(feelsLike) + " °C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";
                        weatherView.setText(output);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}