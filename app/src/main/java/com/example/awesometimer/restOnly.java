package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class restOnly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If user has same exercise times but unique rest
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_only);

        Intent intent = getIntent(); //Get INFO intent;
        int[] info = intent.getIntArrayExtra("INFO"); //get intent and set reference array
        for(int element: info) //Debug code to check if data is getting passed correctly
            System.out.println(element);
    }
}
