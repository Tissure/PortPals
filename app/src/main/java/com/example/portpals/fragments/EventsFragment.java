package com.example.portpals.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.portpals.R;
import com.example.portpals.models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class EventsFragment extends Fragment {

    private final Map<Integer, Fragment> fragmentMap;

    public EventsFragment() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.list2, new ChatFragment());
        fragmentMap.put(R.id.map2, new MapFragment());
    }




    @Override
    public void onResume() {
        super.onResume();
        getParentFragmentManager().beginTransaction().replace(R.id.eventContainer, Objects.requireNonNull(fragmentMap.get(R.id.list2))).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        BottomNavigationView eventNavbar = view.findViewById(R.id.eventNavbar);
        FragmentManager fragmentManager = getParentFragmentManager();


        eventNavbar.setOnItemSelectedListener(item -> {
            Fragment f = fragmentMap.get(item.getItemId());
            if (f == null) {
                Toast.makeText(getContext(),"Null Fragment", Toast.LENGTH_SHORT).show();
                return false;
            }
            fragmentManager.beginTransaction().replace(R.id.eventContainer, f).commit();
            return true;
        });
        return view;
    }
}