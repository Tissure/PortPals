package com.example.portpals.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.env.Secrets;
import com.example.portpals.models.Airport;
import com.example.portpals.models.flight.FlightInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class FlightInfoManager extends ViewModel {
    private static FlightInfoManager instance = null;
    private static final String TAG = "FlightInfoViewModel";

    private MutableLiveData<FlightInfo> flight;
    private MutableLiveData<Airport> departure;
    private MutableLiveData<Airport> arrival;

    private final String URL = "http://api.aviationstack.com/v1/flights?access_key=";
    private final String FLIGHT_NO_TAG = "&flight_number=";
    private final String FLIGHT_IATA_TAG = "&flight_iata=";
    private final String FLIGHT_ICAO_TAG = "&flight_icao=";
    private final String LIMIT = "&limit=1";

    public RequestQueue requestQueue;

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

    public void getFlightInfo(String flightNo) {
        //TODO filter entered flightNo for: No Code, IATA Code, ICAO Code
        String requestURL = URL + Secrets.KEY + FLIGHT_IATA_TAG + flightNo + LIMIT;
        Log.d("URL", requestURL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG + ": ", "somePostRequest Response : " + response.toString());
                if (null != response.toString()) {
                    try {
                        JSONArray data = response.getJSONArray("data");
                        flight.setValue(new Gson().fromJson(data.getJSONObject(0).toString(), FlightInfo.class));
//                        AirportsInfoManager.getInstance().getDeparture(flight.getValue().getDeparture().getIata());
//                        AirportsInfoManager.getInstance().getArrival(flight.getValue().getArrival().getIata());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                });

        requestQueue.add(request);
    }

    public LiveData<FlightInfo> getFlight(String flightNo) {
        if (flight == null) {
            flight = new MutableLiveData<>();
            getFlightInfo(flightNo);
        }
        return flight;
    }

}