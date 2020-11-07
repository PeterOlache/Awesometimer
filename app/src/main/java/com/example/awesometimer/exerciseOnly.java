package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class exerciseOnly extends AppCompatActivity {
    private EditText repsTime;
    private EditText repsRest;
    private EditText setsRest;
    private EditText exerciseName;
    private TextView repsTimeText;
    private TextView restTimeText;
    private TextView setsRestText;
    private int[] info;
    private ArrayList<String> timer = new ArrayList<String>();
    private int count;
    private  String repRest;
    private  String set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If user has unique exercise but the same rest time
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_only);

        count = 1;
        Intent intent = getIntent();
        info = intent.getIntArrayExtra("INFO"); //Get the array containing sets and reps
        for (int element: info) {
            System.out.println(element);
        }

        repsTime = findViewById(R.id.exerciseOnlyLengthEdit);
        repsRest = findViewById(R.id.exerciseOnlyRestEdit);
        setsRest = findViewById(R.id.exerciseOnlyRestSetEdit);
        repsTimeText = findViewById(R.id.exerciseOnlyLengthLabel);
        restTimeText = findViewById(R.id.exerciseOnlyRestLabel);
        setsRestText = findViewById(R.id.exerciseOnlyRestSetLabel);
        exerciseName = findViewById(R.id.exerciseOnlyNameEdit);
    }
    /*
    This will build a String Array List in a specified order designated by
    [# of sets, set rest, exercises rest time, rep 1 length, rep 1 name, rep 2 length, rep 2 name, ... rep n length,
    rep n name]
    Thus when read into timer each pair will represent the exercise time, the name, and the constant
    rest time. 
     */
    public void next(View v){
        System.out.println("next start");
        if (count == 1){
            timer.add(Integer.toString(info[0])); //add # of sets
            timer.add(setsRest.getText().toString()); //add rest between sets
            timer.add(repsRest.getText().toString()); //add rest between reps

            restTimeText.setVisibility(View.INVISIBLE);
            setsRestText.setVisibility(View.INVISIBLE);
            repsRest.setVisibility(View.INVISIBLE);
            setsRest.setVisibility(View.INVISIBLE);
        }
        timer.add(repsTime.getText().toString()); // removed reps variable for condensed code.
        timer.add(exerciseName.getText().toString());

        count++;
        repsTime.setText("0");
        repsTimeText.setText("Exercise " + count + " length");


        /* Keeping this code in case we need it later
        if (count >= info[0]+1){ //if the last exercise has been added, begin setting up array
            int tempCount = 0;
            String[] finTimer = new String[timer.size()*info[0]-1]; //Get reps but -1 for better array management
            for (int i = 0; i < finTimer.length; i++){ //Fill in timer array
                finTimer[i] = timer.get(tempCount); //get value user entered at current position
                if (tempCount == timer.size()-1){
                    tempCount = 0;
                } else{
                    tempCount ++;
                }


            }
            */
            if (count >= info[1] + 1) { //If the number of exercises added is >= the number to add +1
                for(String exercise: timer)
                {
                    System.out.println(exercise);
                }
                Intent intent = new Intent(this, startTimer.class); //Moved intent creation
                intent.putExtra("TIMER", timer); //down here to prevent pointless intent creation
                startActivity(intent);
            }
        }
    }
