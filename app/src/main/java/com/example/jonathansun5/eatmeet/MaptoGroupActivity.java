package com.example.jonathansun5.eatmeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaptoGroupActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maptogroup);
        button = (Button)findViewById(R.id.joingroup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create an intent which navigate to another activity.
            }
        });

    }
}

