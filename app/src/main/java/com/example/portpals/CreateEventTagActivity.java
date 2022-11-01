package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class CreateEventTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_tag);
    }

    public void createEventPart4(View v) {
        Intent intent = new Intent(this, CreateEventSummaryActivity.class);
        Bundle bundle = getIntent().getExtras();
        intent.putExtras(bundle);

        ArrayList<String> checkedBoxes = new ArrayList<>();

        //initiate a check box
        CheckBox checkbox0 = (CheckBox) findViewById(R.id.checkBox);
        if(checkbox0.isChecked()){
            checkedBoxes.add((String) checkbox0.getText());
        }
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkBox2);
        if(checkbox1.isChecked()){
            checkedBoxes.add((String) checkbox1.getText());
        }
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkBox3);
        if(checkbox2.isChecked()){
            checkedBoxes.add((String) checkbox2.getText());
        }
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkBox4);
        if(checkbox3.isChecked()){
            checkedBoxes.add((String) checkbox3.getText());
        }
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkBox5);
        if(checkbox4.isChecked()){
            checkedBoxes.add((String) checkbox4.getText());
        }

        intent.putStringArrayListExtra("tags", checkedBoxes);

        startActivity(intent);
    }
}