package com.example.portpals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.portpals.fragments.FlightInfoFragment;
import com.example.portpals.models.Airport;
import com.example.portpals.models.Event;
import com.example.portpals.models.User;
import com.example.portpals.util.AirportsInfoManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CreateEventSummaryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_create_event_summary);

        String title = bundle.getString("title");
        TextView country = findViewById(R.id.textView1);
        country.setText(title);

        String time = bundle.getString("time");
        TextView ageRange = findViewById(R.id.textView2);
        ageRange.setText(time);

        String desc = bundle.getString("desc");
        TextView description = findViewById(R.id.textView3);
        description.setText(desc);

        String lat = bundle.getString("lat");
        TextView latitude = findViewById(R.id.latTv);
        latitude.setText(lat);

        int capacity = bundle.getInt("capacity");
        TextView capacityTv = findViewById(R.id.capacityTv);
        capacityTv.setText(String.valueOf(capacity));

        String lng = bundle.getString("lng");
        TextView longitude = findViewById(R.id.longTv);
        longitude.setText(lng);

        ArrayList<String> result3 = bundle.getStringArrayList("tags");
        TextView reason = findViewById(R.id.textView4);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < result3.size(); i++){
            s.append(result3.get(i)).append("\n");
        }
        reason.setText(s.toString());
    }

    public void createEventPart5(View v) {
        uploadEvent();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void uploadEvent() {
        Bundle bundle = getIntent().getExtras();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String iata = FlightInfoFragment.getIata();

        // gather the event info and make a new event JSON object and add it to the events list under the specific IATA
        Event newEvent = new Event();
        String id = databaseReference.push().getKey();
        newEvent.setId(id);
        newEvent.setOccupants(1);
        newEvent.setCapacity(bundle.getInt("capacity"));
        newEvent.setName(bundle.getString("title"));
        newEvent.setTags(bundle.getStringArrayList("tags"));
        newEvent.setUpTime(bundle.getString("time"));
        newEvent.setLatitude(bundle.getString("lat"));
        newEvent.setLongitude(bundle.getString("lng"));
        newEvent.setDescription(bundle.getString("desc"));
        newEvent.setIata(iata);

        // obtain the current logged in user's data and shove it in the event
        Query creatorUserQuery = databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Gson gson = new Gson();
        creatorUserQuery.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User creatorUser = gson.fromJson(gson.toJson(task.getResult().getValue()), User.class);
                if (creatorUser == null) {
                    System.out.println("No user signed in!");
                } else {
                    newEvent.setUser(creatorUser);
                }

                // upload the event to the firebase database
                assert id != null;
                databaseReference.child("Events").child(id).setValue(newEvent);
            }
        });
    }

}