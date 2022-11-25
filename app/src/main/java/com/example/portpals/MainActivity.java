package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.portpals.chat.ChatAdapter;
import com.example.portpals.chat.ChatList;
import com.example.portpals.fragments.ChatFragment;
import com.example.portpals.fragments.EventFragment;
import com.example.portpals.fragments.GlobalChat;
import com.example.portpals.fragments.HomeFragment;
import com.example.portpals.fragments.MapFragment;
import com.example.portpals.fragments.ProfileFragment;
import com.example.portpals.util.AirportsInfoManager;
import com.example.portpals.util.FlightInfoManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static Context context;

    private BottomNavigationView bottomNavBar;
    private Map<Integer, Fragment> fragmentMap;

    public MainActivity() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, new HomeFragment());
        fragmentMap.put(R.id.chat, new ChatFragment());
        fragmentMap.put(R.id.globalChat, new GlobalChat());
        fragmentMap.put(R.id.event, new EventFragment());
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
            bottomNavBar = findViewById(R.id.bottomNavBar);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMap.get(R.id.home)).commit();

            bottomNavBar.setOnItemSelectedListener(item -> {
                    Fragment f = fragmentMap.get(item.getItemId());
                if (f == null) {
                    return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
                return true;
            });
        } else {
            System.out.println("user not signed in!");
            Intent goSignIn = new Intent(this, SignInActivity.class);
            startActivity(goSignIn);
        }



    }

    public static Context getContext(){
        return context;
    }

}