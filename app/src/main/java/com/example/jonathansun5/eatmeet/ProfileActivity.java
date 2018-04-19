package com.example.jonathansun5.eatmeet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText usernameText;
    private EditText lifestyleText;
    private EditText birthyearText;
    private EditText partysizeText;
    private EditText allergiesText;

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
        setContentView(R.layout.activity_profile);

        //Link up UI elements
        nameText = (EditText) findViewById(R.id.nameEditText);
        emailText = (EditText) findViewById(R.id.emailEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);
        usernameText = (EditText) findViewById(R.id.usernameEditText);
        lifestyleText = (EditText) findViewById(R.id.lifestyleEditText);
        birthyearText = (EditText) findViewById(R.id.birthyearEditText);
        partysizeText = (EditText) findViewById(R.id.partysizeEditText);
        allergiesText = (EditText) findViewById(R.id.allergiesEditText);

        //Get Intent information
        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");
        username = (String) extras.get("username");
        lifestyle = (String) extras.get("lifestyle");
        birthYear = (String) extras.get("birthyear");
        partySize = (String) extras.get("partysize");
        allergies = (ArrayList<String>) extras.get("allergies");

        //Set profile information
        nameText.setText(name, TextView.BufferType.EDITABLE);
        emailText.setText(email, TextView.BufferType.EDITABLE);
        passwordText.setText(password, TextView.BufferType.EDITABLE);
        usernameText.setText(username, TextView.BufferType.EDITABLE);
        lifestyleText.setText(lifestyle, TextView.BufferType.EDITABLE);
        birthyearText.setText(birthYear, TextView.BufferType.EDITABLE);
        partysizeText.setText(partySize, TextView.BufferType.EDITABLE);
        String allergyFull = "";
        for(int i = 0; i < allergies.size(); i++){
            allergyFull = allergyFull + allergies.get(i) + ", ";
        }
        allergiesText.setText(allergyFull, TextView.BufferType.EDITABLE);

    }
}
