package com.example.portpals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.portpals.fragments.ChatFragment;
import com.example.portpals.fragments.EventFragment;
import com.example.portpals.fragments.HomeFragment;
import com.example.portpals.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;

    HomeFragment homeFragment = new HomeFragment();
    ChatFragment chatFragment = new ChatFragment();
    EventFragment eventFragment = new EventFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavBar = findViewById(R.id.bottomNavBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;
                case R.id.chat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,chatFragment).commit();
                    return true;
                case R.id.event:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,eventFragment).commit();
                    return true;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                    return true;
            }
                return false;
            }
        });
    }

    //        Button toHomeBtn = findViewById(R.id.homeNavBtn);
//        toHomeBtn.setTextColor(Color.parseColor("black"));
//        toHomeBtn.setBackgroundColor(Color.parseColor("magenta"));
//        toHomeBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        });

//        Button toChatBtn = findViewById(R.id.chatNavBtn);
//        toChatBtn.setTextColor(Color.parseColor("black"));
//        toChatBtn.setBackgroundColor(Color.parseColor("magenta"));
//        toChatBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(this, Chat.class);
//            startActivity(intent);
//        });
//
//        Button toEventBtn = findViewById(R.id.eventNavBtn);
//        toEventBtn.setTextColor(Color.parseColor("black"));
//        toEventBtn.setBackgroundColor(Color.parseColor("magenta"));
//        toEventBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(this, Events.class);
//            startActivity(intent);
//        });
//
//        Button toProfileBtn = findViewById(R.id.profileNavBtn);
//        toProfileBtn.setTextColor(Color.parseColor("black"));
//        toProfileBtn.setBackgroundColor(Color.parseColor("magenta"));
//        toProfileBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(this, Profile.class);
//            startActivity(intent);
//        });

}