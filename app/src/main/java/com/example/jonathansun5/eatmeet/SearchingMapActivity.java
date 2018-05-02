package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchingMapActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int status = 0;
    String username;
    CountDownTimer mCountDownTimer;
    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchingmap);
        ButterKnife.bind(this);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(status);
        mCountDownTimer = new MyCountDownTimer(10000,2000);
        mCountDownTimer.start();

        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        username = (String) extras.get("username");

        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, SetLocationActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
        });

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
            Intent intent = new Intent(mContext,  MaptoGroupActivity.class);
            intent.putExtra("username", username);
            mContext.startActivity(intent);
            startActivity(intent);

        }

    }

}
