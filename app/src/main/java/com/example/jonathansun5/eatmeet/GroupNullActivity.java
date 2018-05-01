package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupNullActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupnull);
        ButterKnife.bind(this);

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
}

