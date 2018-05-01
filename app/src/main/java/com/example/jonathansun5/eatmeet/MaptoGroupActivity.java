package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaptoGroupActivity extends AppCompatActivity {
    private Button button;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maptogroup);
        button = (Button)findViewById(R.id.joingroup);
        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        username = (String) extras.get("username");

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

