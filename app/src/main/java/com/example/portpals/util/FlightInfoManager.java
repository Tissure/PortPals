package com.example.portpals.util;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.portpals.env.Secrets;

import org.json.JSONObject;

import java.util.Date;

public class FlightInfoManager extends ViewModel {
    private final String url = "http://api.aviationstack.com/v1/flights?access_key=";
    private final String flightNoTag = "&flight_number=";
    private final String flightIataTag = "&flight_iata=";
    private final String flightIcaoTag = "&flight_icao=";
    private static FlightInfoManager instance = null;
    private static final String TAG = "FlightInfoViewModel";

    public RequestQueue requestQueue;

    String departureAirport, arrivalAirport;
    Date departureTime, arrivalTime;

    private FlightInfoManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized FlightInfoManager getInstance(Context context) {
        if (null == instance)
            instance = new FlightInfoManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized FlightInfoManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(FlightInfoManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }
    // TODO: Implement the ViewModel

    public void getFlightInfo(String flightNo, final RequestListener<String> listener) {

        //TODO filter entered flightNo for: No Code, IATA Code, ICAO Code
        String requestURL = url + Secrets.KEY + flightNoTag + flightNo;
        Log.d("URL", requestURL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                if (null != response.toString())
                    listener.getResult(response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult(String.valueOf(false));
                        }
                    }
                });

        requestQueue.add(request);
    }
}