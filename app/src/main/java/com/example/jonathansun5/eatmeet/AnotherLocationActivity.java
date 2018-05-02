package com.example.jonathansun5.eatmeet;


import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AnotherLocationActivity extends AppCompatActivity {
    ProgressBar progressBar;
    CountDownTimer mCountDownTimer;
    private int status = 0;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotherlocation);
        progressBar = findViewById(R.id.progressBar1);
        progressBar.setProgress(status);
        mCountDownTimer = new AnotherLocationActivity.MyCountDownTimer(6000,2000);
        mCountDownTimer.start();

        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        username = (String) extras.get("username");

    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Toast.makeText(getApplicationContext(), "Start searching...", Toast.LENGTH_SHORT).show();

            int progress = (int) (millisUntilFinished/100);
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            Toast.makeText(getApplicationContext(), "Searching complete.", Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
            Context mContext = getBaseContext();
            Intent intent = new Intent(mContext,  GroupNullActivity.class);
            intent.putExtra("username", username);
            mContext.startActivity(intent);

        }

    }


}

