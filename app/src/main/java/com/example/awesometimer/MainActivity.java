package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView sets;
    private TextView reps;
    private CheckBox uE;
    private CheckBox uR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sets = findViewById(R.id.sets);
        reps = findViewById(R.id.reps);
        uE = findViewById(R.id.uniqueExercise);
        uR = findViewById(R.id.uniqueRest);
    }

    public void addTime(View v) {
        System.out.println("addTime");
        int numSets = Integer.parseInt(sets.getText().toString());
        int numReps = Integer.parseInt(reps.getText().toString());
        int uniqueEx = (uE.isChecked()) ? 1 : 0 ;
        int uniqueRe = (uR.isChecked()) ? 1 : 0 ;
        int[] info = {numSets, numReps, uniqueEx, uniqueRe};
        Intent intent;
        if (uniqueEx == 1 && uniqueRe == 0) {
            intent = new Intent(this, exerciseOnly.class);
        } else if (uniqueEx == 0 && uniqueRe == 1){
            intent = new Intent(this, restOnly.class);
        } else if (uniqueEx == 1 && uniqueRe == 1){
            intent = new Intent(this, exerciseAndRest.class);
        } else{
            intent = new Intent(this, noneUnique.class);
        }
        intent.putExtra("info", info);

        startActivity(intent);
    }
}
