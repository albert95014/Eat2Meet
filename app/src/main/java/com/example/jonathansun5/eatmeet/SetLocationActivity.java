package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.List;

public class SetLocationActivity extends AppCompatActivity {
    ImageButton imageButton;
    EditText editText;
    Button button;
    String username;
    List<Address> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlocation);
        imageButton = findViewById(R.id.go);
        editText = findViewById(R.id.anotheraddress);
        button = findViewById(R.id.currentlocation);
        String address = editText.getText().toString();
        Geocoder gc = new Geocoder(this);
        if(Geocoder.isPresent()) {
            try {
                List<Address> list = gc.getFromLocationName(address,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (list != null) {
            Address address1 = list.get(0);
            double lat = address1.getLatitude();
            double lon = address1.getLongitude();
        }

        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        username = (String) extras.get("username");

        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext,  AnotherLocationActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext,  SearchingMapActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
        });
    }
}

