package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView textView;
    SeekBar timerSeekBar;
    boolean counterisactive = false;
    Button gobutton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerTextView.setText("0:10:0");
        timerSeekBar.setProgress(600);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        gobutton.setText("GO!");
        counterisactive = false;

    }
    public void buttonClicked(View view) {

        textView.setText("");

        if (counterisactive) {
            resetTimer();
        } else {
            counterisactive = true;
            timerSeekBar.setEnabled(false);
            gobutton.setText("STOP!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {


                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mediaPlayer.start();
                    textView.setText("ab");

                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){

        int hours = secondsLeft/3600;
        int minutes = (secondsLeft -hours*3600)/60;
        int seconds = secondsLeft - minutes*60-3600*hours;
        String secondString = Integer.toString(seconds);

        if (seconds<10){
            secondString= "0" + secondString;
        }
        timerTextView.setText(Integer.toString(hours)+":"+Integer.toString(minutes)+ ":" + secondString);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        textView= findViewById(R.id.textView);
        timerTextView = findViewById(R.id.countdownTextView);
        gobutton = findViewById(R.id.goButton);
        timerSeekBar.setMax(7200);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}