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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_only);

        Intent intent = getIntent();
        int[] info = intent.getIntArrayExtra("info");
        for (int element: info) {
            System.out.println(element);
        }
    }
}
