package com.example.portpals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEventPinMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_pin_map);
    }

    public void createEventPart3(View v) {
        Intent intent = new Intent(this, CreateEventTagActivity.class);
        Bundle bundle = getIntent().getExtras();
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
