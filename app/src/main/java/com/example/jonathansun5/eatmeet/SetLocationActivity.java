package com.example.jonathansun5.eatmeet;

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
    List<Address> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlocation);
        imageButton = (ImageButton)findViewById(R.id.go);
        editText = (EditText)findViewById(R.id.anotheraddress);
        button = (Button)findViewById(R.id.currentlocation);
        String address = editText.getText().toString();
        Geocoder gc = new Geocoder(this);
        if(gc.isPresent()) {
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

        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SetLocationActivity.this, AnotherLocationActivity.class);
                startActivity(intent);


            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SetLocationActivity.this, SearchingMapActivity.class);
                startActivity(intent);
                //do something
            }
        });
    }
}

