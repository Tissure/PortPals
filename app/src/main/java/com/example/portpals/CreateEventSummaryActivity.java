package com.example.portpals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        ArrayList<String> result3 = bundle.getStringArrayList("tags");
        TextView reason = findViewById(R.id.textView4);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < result3.size(); i++){
            s.append(result3.get(i)).append("\n");
        }
        reason.setText(s.toString());
    }

    public void createEventPart5(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        //TODO: take the data from the bundle using strings and add to firebase structure

        startActivity(intent);
    }

}
