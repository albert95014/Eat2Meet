package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

public class AllergyActivity extends AppCompatActivity {

    private Button allergyContinue;

    private String name;
    private String email;
    private String password;
    private String username;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);

        //Link up UI elements
        allergyContinue = (Button) findViewById(R.id.allergyContinue);

        //Get Extras
        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");
        username = (String) extras.get("username");
        lifestyle = (String) extras.get("lifestyle");
        birthYear = (String) extras.get("birthyear");
        partySize = (String) extras.get("partysize");

        allergies = new ArrayList<>();

        allergyContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, ProfileActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("birthyear", birthYear);
                intent.putExtra("partysize", partySize);
                intent.putExtra("lifestyle", lifestyle);
                intent.putExtra("allergies", allergies);
                mContext.startActivity(intent);

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Log.d("Checked", "Added to Allergies");

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioEggs:
                if (checked)
                    allergies.add("eggs");
                break;
            case R.id.radioDairy:
                if (checked)
                    allergies.add("dairy");
                break;
            case R.id.radioPineNuts:
                if (checked)
                    allergies.add("pine nuts");
                break;
            case R.id.radioPeanuts:
                if (checked)
                    allergies.add("peanuts");
                break;
            case R.id.radioSesame:
                if (checked)
                    allergies.add("sesame");
                break;
            case R.id.radioFish:
                if (checked)
                    allergies.add("fish");
                break;
            case R.id.radioGluten:
                if (checked)
                    allergies.add("gluten");
                break;
        }
    }
}
