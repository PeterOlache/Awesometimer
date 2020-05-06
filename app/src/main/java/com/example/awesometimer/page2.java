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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class page2 extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private Button addTimeButton;
    private CountDownTimer countDownTimer;
    private EditText editText;
    private boolean timerRunning;
    private long timeLeftInMilliseconds;
    private LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        editText = findViewById(R.id.timerLength);
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);
        addTimeButton =  findViewById(R.id.addTime);
        view = findViewById(R.id.linearLayout);
        Intent intent = getIntent();
        int[] info = intent.getIntArrayExtra("info");
        for (int element: info) {
            System.out.println(element);
        }

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTime();
            }
        });

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startStop();
            }
        });
    }

    public void addTime() {
        String temp=editText.getText().toString();
        if (!"".equals(temp)){
            timeLeftInMilliseconds=Long.parseLong(temp);
            timeLeftInMilliseconds = timeLeftInMilliseconds * 1000;
            updateTimer();
        }
    }

    public void startStop() {
        if(timerRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {

            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
        countdownButton.setText("PAUSE");
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        countdownButton.setText("START");
    }

    public void updateTimer(){
        long displaytime = timeLeftInMilliseconds - 1000;
        int minutes = (int) displaytime / 60000;
        int seconds = (int) displaytime % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes + ":";

        if (seconds < 10){
            timeLeftText += "0";
        }

        timeLeftText += seconds;
        countdownText.setText(timeLeftText);

        if (seconds == 0){
            alarm();
        }
    }

    public void alarm(){
        view.setBackgroundColor(Color.argb(255, 255, 0, 0));
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }
}