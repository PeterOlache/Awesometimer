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
        sets = findViewById(R.id.sets); //initialize sets text field
        reps = findViewById(R.id.reps); //initialize reps text field
        uE = findViewById(R.id.uniqueExercise); //initialize unique exercise checkbox
        uR = findViewById(R.id.uniqueRest); //initialize unique rest time checkbox
    }

    public void addTime(View v) {
        int numSets = Integer.parseInt(sets.getText().toString()); //get sets
        int numReps = Integer.parseInt(reps.getText().toString()); //get reps
        int uniqueEx = (uE.isChecked()) ? 1 : 0 ; //if uE is checked true, else false
        int uniqueRe = (uR.isChecked()) ? 1 : 0 ; //if uR is checked true, else false
        int[] info = {numSets, numReps, uniqueEx, uniqueRe}; //create array to determine intent
        Intent intent;
        if (uniqueEx == 1 && uniqueRe == 0) { //If user has unique exercise but the same rest time
            intent = new Intent(this, exerciseOnly.class);
        } else if (uniqueEx == 0 && uniqueRe == 1){ //If user has same exercise times but unique rest
            intent = new Intent(this, restOnly.class);
        } else if (uniqueEx == 1 && uniqueRe == 1){ //If user has unique exercise and rest
            intent = new Intent(this, exerciseAndRest.class);
        } else{ //if user has same exercise time and same rest time
            intent = new Intent(this, noneUnique.class);
        }
        intent.putExtra("INFO", info); //Capitalized because after put extra data is immutable
        startActivity(intent);
    }
}
