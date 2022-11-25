package com.example.portpals.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.portpals.Chat;
import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.chat.ChatList;
import com.example.portpals.util.AirportsInfoManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class MapFragment extends Fragment {

    private final DatabaseReference db = MainActivity.databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        String iata = Chat.getIata();

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("Airports")) {
                            if(snapshot.child("Airports").child(iata).hasChild("lat")) {
                                Double airportLat = snapshot.child("Airports").child(iata).child("lat").getValue(Double.class);
                                Double airportLon = snapshot.child("Airports").child(iata).child("lon").getValue(Double.class);
                                LatLng airport = new LatLng(airportLat,airportLon);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(airport,13));
                                if(snapshot.child("Airports").child(iata).hasChild("Events")){
                                    for(DataSnapshot eventSnapShot : snapshot.child("Airports").child(iata).child("Events").getChildren()) {

                                        double latitude = Double.parseDouble(Objects.requireNonNull(eventSnapShot.child("latitude").getValue(String.class)));
                                        double longitude = Double.parseDouble(Objects.requireNonNull(eventSnapShot.child("longitude").getValue(String.class)));
                                        String eventName = eventSnapShot.child("name").getValue(String.class);
                                        LatLng event = new LatLng(latitude, longitude);
                                        googleMap.addMarker(new MarkerOptions().position(event).title(eventName));
                                    }
                                }

                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        return view;
    }
}