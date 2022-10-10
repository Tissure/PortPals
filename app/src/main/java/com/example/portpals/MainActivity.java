package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.portpals.fragments.ChatFragment;
import com.example.portpals.fragments.EventFragment;
import com.example.portpals.fragments.HomeFragment;
import com.example.portpals.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;
    Map<Integer, Fragment> fragmentMap;

    public MainActivity() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, new HomeFragment());
        fragmentMap.put(R.id.chat, new ChatFragment());
        fragmentMap.put(R.id.event, new EventFragment());
        fragmentMap.put(R.id.profile, new ProfileFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

}