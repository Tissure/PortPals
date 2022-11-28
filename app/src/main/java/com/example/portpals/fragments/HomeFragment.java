package com.example.portpals.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Locale;


public class HomeFragment extends Fragment {
//    TextView tvResult;
//    //    http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=61ecd5de90be5cf0137e8f56761899b2
//    private final String url = "http://api.openweathermap.org/data/2.5/weather";
//    private final String appid = "61ecd5de90be5cf0137e8f56761899b2";
//    DecimalFormat df = new DecimalFormat("#.##");
//    private String city;
//    DatabaseReference db = MainActivity.databaseReference;

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



//        tvResult = view.findViewById(R.id.tvResult);
//        fl.addView(tvResult);
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getWeatherDetails();
//    }
//
//    public void getWeatherDetails() {
//        String iata = FlightInfoFragment.getIata();
//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                city = snapshot.child("Airports").child(iata).child("city").getValue(String.class).toLowerCase(Locale.ROOT);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        String tempUrl = url + "?q=" + "vancouver" + "&APPID=" + appid;
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                String output = "";
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);
//                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
//                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
//                    String description = jsonObjectWeather.getString("description");
//                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
//                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
//                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
//                    float pressure = jsonObjectMain.getInt("pressure");
//                    int humidity = jsonObjectMain.getInt("humidity");
//                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
//                    String wind = jsonObjectWind.getString("speed");
//                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
//                    String clouds = jsonObjectClouds.getString("all");
//                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
//                    String countryName = jsonObjectSys.getString("country");
//                    String cityName = jsonResponse.getString("name");
//                    tvResult.setTextColor(Color.rgb(68,134,199));
//                    output += "Current weather of " + cityName + " (" + countryName + ")"
//                            + "\n Temp: " + df.format(temp) + " °C"
//                            + "\n Feels Like: " + df.format(feelsLike) + " °C"
//                            + "\n Humidity: " + humidity + "%"
//                            + "\n Description: " + description
//                            + "\n Wind Speed: " + wind + "m/s (meters per second)"
//                            + "\n Cloudiness: " + clouds + "%"
//                            + "\n Pressure: " + pressure + " hPa";
//                    tvResult.setText(output);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener(){
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);
//    }

}