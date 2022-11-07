package com.example.portpals.util;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.android.volley.toolbox.Volley;
import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.models.Airport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

public class AirportsInfoManager  extends ViewModel {
    private static AirportsInfoManager instance = null;
    private static final String TAG = "AirportViewModel";

    private MutableLiveData<Airport> departure;
    private MutableLiveData<Airport> arrival;

    private AirportsInfoManager(){};

    public static synchronized AirportsInfoManager getInstance(String departureCode, String arrivalCode) {
        if (null == instance){
            instance = new AirportsInfoManager();
            instance.getDeparture(departureCode);
            instance.getArrival(arrivalCode);
        }
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized AirportsInfoManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(AirportsInfoManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }


    public LiveData<Airport> getDeparture(String departureCode) {
        if(departure == null){
            departure = new MutableLiveData<Airport>();
            Gson gson = new Gson();
            Query departureAirport =  MainActivity.databaseReference.orderByChild(MainActivity.getContext().getString(R.string.fb_airports)).equalTo(departureCode);
            departureAirport.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot child : task.getResult().getChildren()){
                            departure.setValue(gson.fromJson(gson.toJson(child.getValue()), Airport.class));
                        }
                    }
                }
            });
        }
        return departure;
    }
    public LiveData<Airport> getArrival(String arrivalCode) {
        if(arrival == null){
            arrival = new MutableLiveData<Airport>();
            Query arrivalAirport =  MainActivity.databaseReference.orderByChild(MainActivity.getContext().getString(R.string.fb_airports)).equalTo(arrivalCode);
            Gson gson = new Gson();
            arrivalAirport.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        for(DataSnapshot child : task.getResult().getChildren()){
                            arrival.setValue(gson.fromJson(gson.toJson(child.getValue()), Airport.class));
                        }
                    }
                }
            });
        }
        return arrival;
    }
}
