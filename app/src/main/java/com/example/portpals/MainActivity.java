package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.portpals.fragments.EventsFragment;
import com.example.portpals.fragments.FlightInfoFragment;
import com.example.portpals.fragments.ProfileFragment;
import com.example.portpals.util.FlightInfoManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static Context context;

    private final Map<Integer, Fragment> fragmentMap;

    public MainActivity() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, new FlightInfoFragment());
        fragmentMap.put(R.id.events, new EventsFragment());
        fragmentMap.put(R.id.globalChat, null);
        fragmentMap.put(R.id.profile, new ProfileFragment());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();
        FlightInfoManager.getInstance(this);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            System.out.println("user signed in!");
            System.out.println(currentUser.getEmail());
            BottomNavigationView bottomNavBar = findViewById(R.id.bottomNavBar);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, Objects.requireNonNull(fragmentMap.get(R.id.home))).commit();

            bottomNavBar.setOnItemSelectedListener(item -> {
                Fragment f = fragmentMap.get(item.getItemId());
                if (f == null) {
                    return false;
                }
                if(FlightInfoFragment.getIata() == null) {
                    Toast.makeText(this, "Must enter flight number first!", Toast.LENGTH_LONG).show();
                } else if (item.getItemId() == R.id.globalChat) {
                    Intent intent = new Intent(this, Chat.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chatType", "airportChat");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
                }
                return true;
            });
        } else {
            System.out.println("user not signed in!");
            Intent goSignIn = new Intent(this, SignInActivity.class);
            startActivity(goSignIn);
        }
    }

    public static Context getContext() {
        return context;
    }

}