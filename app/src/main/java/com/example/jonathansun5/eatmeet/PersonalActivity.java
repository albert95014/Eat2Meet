package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.birthYear) EditText _birthYearText;
    @BindView(R.id.partySize) EditText _partySizeText;
    @BindView(R.id.personalContinue) Button _personalContinueButton;

    private String name;
    private String email;
    private String password;
    private String username;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);

        //Get intent information
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

        if (birthYear != null) {
            _birthYearText.setText(birthYear);
        }
        if (partySize != null) {
            _partySizeText.setText(partySize);
        }
        if (lifestyle != null) {
            switch (lifestyle) {
                case "Vegetarian":
                    RadioButton vegetarian = (RadioButton) findViewById(R.id.radioVegetarian);
                    vegetarian.setChecked(true);
                    break;
                case "Vegan":
                    RadioButton vegan = (RadioButton) findViewById(R.id.radioVegan);
                    vegan.setChecked(true);
                    break;
                case "Gluten Free":
                    RadioButton glutenFree = (RadioButton) findViewById(R.id.radioGlutenFree);
                    glutenFree.setChecked(true);
                    break;
                case "Omnivore":
                    RadioButton omnivore = (RadioButton) findViewById(R.id.radioOmnivore);
                    omnivore.setChecked(true);
                    break;
                case "Pescatarian":
                    RadioButton pescatarian = (RadioButton) findViewById(R.id.radioPescatarian);
                    pescatarian.setChecked(true);
                    break;
                case "Normal":
                    RadioButton normal = (RadioButton) findViewById(R.id.radioNormal);
                    normal.setChecked(true);
                    break;
            }
        }

        _personalContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean continueOkay = false;
                birthYear = _birthYearText.getText().toString();
                partySize = _partySizeText.getText().toString();

                if (birthYear.isEmpty() || birthYear.length() != 4) {
                    continueOkay = false;
                    _birthYearText.setError("enter a valid year");
                } else {
                    continueOkay = true;
                    _birthYearText.setError(null);
                }

                if (partySize.isEmpty() || Integer.valueOf(partySize) < 2 || partySize.length() > 2) {
                    continueOkay = false;
                    _partySizeText.setError("enter a valid party size");
                } else {
                    continueOkay = true;
                    _partySizeText.setError(null);
                }

                if (continueOkay) {
                    Context mContext = getBaseContext();
                    Intent intent = new Intent(mContext, AllergyActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("allergies", allergies);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Please Fix Inputs", Toast.LENGTH_LONG).show();
                }
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
            case R.id.radioNormal:
                if(checked)
                    // Normal chosen
                    lifestyle = "Normal";
                    break;
        }
    }
}
