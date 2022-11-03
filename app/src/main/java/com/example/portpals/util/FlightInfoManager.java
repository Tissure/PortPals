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

import org.json.JSONObject;

import java.util.Date;

public class FlightInfoManager extends ViewModel {
    private final String url = "https://api.aviationstack.com/v1/flights?access_key=";
    private final String flightNo = "&flight_number=";
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

    public void getFlightInfo(String url, final RequestListener<String> listener)    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                        if(null != response.toString())
                            listener.getResult(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (null != error.networkResponse)
                        {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                            listener.getResult(String.valueOf(false));
                        }
                    }
                });

        requestQueue.add(request);
    }
}