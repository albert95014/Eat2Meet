package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {

    private EditText birthYearText;
    private EditText partySizeText;
    private Button personalContinue;

    private String name;
    private String email;
    private String password;
    private String username;
    private String lifestyle;
    private String birthYear;
    private String partySize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        //Link up the UI elements
        birthYearText = (EditText) findViewById(R.id.birthYear);
        partySizeText = (EditText) findViewById(R.id.partySize);
        personalContinue = (Button) findViewById(R.id.personalContinue);

        //Get intent information
        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");
        username = (String) extras.get("username");

        personalContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birthYear = birthYearText.getText().toString();
                partySize = partySizeText.getText().toString();
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, ProfileActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("birthyear", birthYear);
                intent.putExtra("partysize", partySize);
                intent.putExtra("lifestyle", lifestyle);
                mContext.startActivity(intent);

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioVegetarian:
                if (checked)
                    // Vegetarian chosen
                    lifestyle = "Vegetarian";
                    break;
            case R.id.radioVegan:
                if (checked)
                    // Vegan chose
                    lifestyle = "Vegan";
                    break;
            case R.id.radioGlutenFree:
                if (checked)
                    // Gluten Free chosen
                    lifestyle = "Gluten Free";
                    break;
            case R.id.radioOmnivore:
                if (checked)
                    // Omnivore chosen
                    lifestyle = "Omnivore";
                    break;
            case R.id.radioPescatarian:
                if (checked)
                    // Pescatarian chosen
                    lifestyle = "Pescatarian";
                    break;
        }
    }
}
