package com.example.portpals;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class  CreateEventPinMapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    SupportMapFragment mapFragment;
    Marker mCurrLocationMarker;
    Marker eventLocation;
    Location mLastLocation;
    GoogleMap myMap;

    private static final int LOCATION_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_pin_map);

        if (isLocationPermissionGranted()) {
            buildGoogleMaps();
        } else {
            requestLocationPermission();
            buildGoogleMaps();
        }
    }

    private void buildGoogleMaps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        String MAP_FRAGMENT = "map_fragment";

        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager
                .findFragmentByTag(MAP_FRAGMENT); // Check if map already exists

        if (mapFragment == null) {
            // Create new Map instance if it doesn't exist
            mapFragment = SupportMapFragment.newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.map, mapFragment, MAP_FRAGMENT)
                    .commit();
        }
        mapFragment.getMapAsync(this);
    }

    private boolean isLocationPermissionGranted() {
        boolean permissionGranted = true;
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission();
        }
        return permissionGranted;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_CODE);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //TODO: Set camera to specific airport from bundle which grabs LAT and LONG from firebase.
//        LatLng comp3717Lecture = new LatLng(55.25010954461797, -123.00275621174804);
//        googleMap.addMarker(new MarkerOptions().position(comp3717Lecture));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(comp3717Lecture, 15));
        myMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            myMap.setMyLocationEnabled(true);
            myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    myMap.clear();
                    eventLocation = myMap.addMarker(new MarkerOptions().position(point));
                }
            });
        }
    }

    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = myMap.addMarker(markerOptions);

        //move map camera
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

    }

    public void createEventPart3(View v) {
        Intent intent = new Intent(this, CreateEventTagActivity.class);
        Bundle bundle = getIntent().getExtras();

        String lat;
        String lng;

        if (eventLocation == null) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, false);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(bestProvider);
            lat = String.valueOf(location.getLatitude());
            lng = String.valueOf(location.getLongitude());
        } else {
            LatLng markerLocation = eventLocation.getPosition();
            lat = String.valueOf(markerLocation.latitude);
            lng = String.valueOf(markerLocation.longitude);
        }

        bundle.putString("lat", lat);
        bundle.putString("lng", lng);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
