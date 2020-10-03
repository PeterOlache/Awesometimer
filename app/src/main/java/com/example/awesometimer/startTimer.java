package com.example.awesometimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class startTimer extends AppCompatActivity {
    private int[] timer ;
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private TextView countdownText;
    private Button countdownButton;
    private int countCountdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_timer);
        countdownText = findViewById(R.id.time);
        countdownButton = findViewById(R.id.startStop);
        Intent intent = getIntent();
        timer = intent.getIntArrayExtra("timer");
        System.out.println("TIMER PAGE");
        System.out.println(timer[0]);
        timeLeftInMilliseconds = timer[0] * 1000;
        countCountdown = 1;

    }

    public void startStop(View v) {
        System.out.println("in StartStop");
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
        System.out.println("update");

        long displaytime = timeLeftInMilliseconds;
        int minutes = (int) displaytime / 60000;
        int seconds = (int) displaytime % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes + ":";

        if (seconds < 10){
            timeLeftText += "0";
        }

        timeLeftText += seconds;
        countdownText.setText(timeLeftText);

        if (seconds < 1){
            alarm();
            if (countCountdown < timer.length) {
                System.out.println("TimeLEFT " + timer[countCountdown]);
                timeLeftInMilliseconds = timer[countCountdown]*1000;
                countCountdown ++;
                startTimer();
            }

        }
    }

    public void alarm(){
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }
}