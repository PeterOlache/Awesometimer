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
    private TextView repsTimeText;
    private TextView restTimeText;
    private TextView setsRestText;
    private int[] info;
    private ArrayList<Integer> timer = new ArrayList<Integer>();
    private int count;
    private  int repRest;
    private  int set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If user has unique exercise but the same rest time
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_only);

        count = 1;
        Intent intent = getIntent();
        info = intent.getIntArrayExtra("INFO");
        for (int element: info) {
            System.out.println(element);
        }

        repsTime = findViewById(R.id.exerciseOnlyLengthEdit);
        repsRest = findViewById(R.id.exerciseOnlyRestEdit);
        setsRest = findViewById(R.id.exerciseOnlyRestSetEdit);
        repsTimeText = findViewById(R.id.exerciseOnlyLengthLabel);
        restTimeText = findViewById(R.id.exerciseOnlyRestLabel);
        setsRestText = findViewById(R.id.exerciseOnlyRestSetLabel);
    }

    public void next(View v){
        System.out.println("next start");
        int rep = Integer.parseInt(repsTime.getText().toString());
        if (count == 1){
            repRest = Integer.parseInt(repsRest.getText().toString());
            set= Integer.parseInt(setsRest.getText().toString());
            restTimeText.setVisibility(View.INVISIBLE);
            setsRestText.setVisibility(View.INVISIBLE);
            repsRest.setVisibility(View.INVISIBLE);
            setsRest.setVisibility(View.INVISIBLE);
        }
        timer.add(rep);
        if (count < info[0]) {
            System.out.println("IN IF");
            timer.add(repRest);
        }else{
            timer.add(set);
        }
        count++;
        repsTime.setText("0");
        repsTimeText.setText("Exercise " + count + " length");
        Intent intent;

        if (count >= info[0]+1){
            int tempCount = 0;
            int[] finTimer = new int[timer.size()*info[1]-1];
            for (int i = 0; i < finTimer.length; i++){
                finTimer[i] = timer.get(tempCount);
                if (tempCount == timer.size()-1){
                    tempCount = 0;
                } else{
                    tempCount ++;
                }
            }
            intent = new Intent(this, startTimer.class);
            intent.putExtra("timer", finTimer);
            startActivity(intent);
        }
    }

}
