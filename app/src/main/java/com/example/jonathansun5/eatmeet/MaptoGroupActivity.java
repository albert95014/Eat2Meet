package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaptoGroupActivity extends AppCompatActivity {
    private Button button;
    private String username;

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maptogroup);
        button = (Button)findViewById(R.id.joingroup);
        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        username = (String) extras.get("username");
        ButterKnife.bind(this);

        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("User Profile");
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

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, CommentFeedActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
        });

    }
}

