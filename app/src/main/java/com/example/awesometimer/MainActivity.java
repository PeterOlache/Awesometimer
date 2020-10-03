package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //global variables for input
    private TextView sets;
    private TextView reps;
    private CheckBox uE;
    private CheckBox uR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // gets the user input
        sets = findViewById(R.id.sets);
        reps = findViewById(R.id.reps);
        uE = findViewById(R.id.uniqueExercise);
        uR = findViewById(R.id.uniqueRest);
        System.out.println("TEST");
    }

    public void addTime(View v) {
        // converts user the user input into usable data ints
        int numSets = Integer.parseInt(sets.getText().toString());
        int numReps = Integer.parseInt(reps.getText().toString());
        // if statements that convert checked for 1 and unchecked for 2
        int uniqueEx = (uE.isChecked()) ? 1 : 0 ;
        int uniqueRe = (uR.isChecked()) ? 1 : 0 ;

        //Data that will be passed to next page
        int[] info = {numSets, numReps, uniqueEx, uniqueRe};
        Intent intent;
        //determins which page they will be redirected to
        if (uniqueEx == 1 && uniqueRe == 0) {
            intent = new Intent(this, exerciseOnly.class);
        } else if (uniqueEx == 0 && uniqueRe == 1){
            intent = new Intent(this, restOnly.class);
        } else if (uniqueEx == 1 && uniqueRe == 1){
            intent = new Intent(this, exerciseAndRest.class);
        } else{
            intent = new Intent(this, noneUnique.class);
        }
        //adds info to the intent so it can be accessed from the next page
        intent.putExtra("info", info);

        startActivity(intent);
    }
}
