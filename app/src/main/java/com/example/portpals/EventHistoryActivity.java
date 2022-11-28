package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.portpals.models.Event;
import com.example.portpals.recycleradapters.ChatRecyclerAdapter;
import com.example.portpals.util.AirportsInfoManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EventHistoryActivity extends AppCompatActivity {

    private ArrayList<Event> eventList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventList = new ArrayList<>();
        //initEventList();

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new ChatRecyclerAdapter(eventList);
        recyclerView.setAdapter(adapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_history);
    }

}