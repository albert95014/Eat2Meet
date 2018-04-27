package com.example.jonathansun5.eatmeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SearchingMapActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int status = 0;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchingmap);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(status);
        mCountDownTimer = new MyCountDownTimer(10000,2000);
        mCountDownTimer.start();


    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Toast.makeText(getApplicationContext(), "start searching.", Toast.LENGTH_SHORT).show();

            int progress = (int) (millisUntilFinished/100);
            progressBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            Toast.makeText(getApplicationContext(), "searching complete.", Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(SearchingMapActivity.this, MaptoGroupActivity.class);
            startActivity(intent);

        }

    }

}
