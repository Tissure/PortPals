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
    private static FlightInfoManager instance = null;
    private static final String TAG = "FlightInfoViewModel";

    private final String URL = "http://api.aviationstack.com/v1/flights?access_key=";
    private final String FLIGHT_NO_TAG = "&flight_number=";
    private final String FLIGHT_IATA_TAG = "&flight_iata=";
    private final String FLIGHT_ICAO_TAG = "&flight_icao=";
    private final String LIMIT = "&limit=1";

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

    public void getFlightInfo(String flightNo, final RequestListener<JSONObject> listener) {

        //TODO filter entered flightNo for: No Code, IATA Code, ICAO Code
        String requestURL = URL + Secrets.KEY + FLIGHT_IATA_TAG + flightNo + LIMIT;
        Log.d("URL", requestURL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                if (null != response.toString())
                    listener.getResult(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult(null);
                        }
                    }
                });

        requestQueue.add(request);
    }
}