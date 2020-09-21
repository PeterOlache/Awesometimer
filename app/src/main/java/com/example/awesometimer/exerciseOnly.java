package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class exerciseOnly extends AppCompatActivity {
    private TextView repsTime;
    private TextView repsRest;
    private TextView setsRest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If user has unique exercise but the same rest time
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_only);

        Intent intent = getIntent();
        int[] info = intent.getIntArrayExtra("INFO"); //get intent and set referance array
        for (int element: info) { //Debug code to check if data is getting passed correctly
            System.out.println(element);
        }


    }
}
