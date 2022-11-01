package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        initializeSpinner();
    }

    private void initializeSpinner() {
        Spinner spinner = findViewById(R.id.spinner1);

        String[] time = getResources().getStringArray(R.array.timeLimits);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, time);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void createEventPart2(View v) {
        TextView title = findViewById(R.id.editEventName);
        Spinner spinner = findViewById(R.id.spinner1);
        TextView desc = findViewById(R.id.editEventBody);

        Intent intent = new Intent(this, CreateEventPinMapActivity.class);
        Bundle bundle = new Bundle();

        String eventTitle = title.getText().toString();
        bundle.putString("title", eventTitle);

        bundle.putString("time", spinner.getSelectedItem().toString());

        String eventDesc = desc.getText().toString();
        bundle.putString("desc", eventDesc);

        intent.putExtras(bundle);

        startActivity(intent);
    }

}