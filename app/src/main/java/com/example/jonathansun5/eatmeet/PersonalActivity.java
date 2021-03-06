package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.my_toolbar2) android.support.v7.widget.Toolbar _mToolbar2;
    @BindView(R.id.birthYear) EditText _birthYearText;
    @BindView(R.id.partySize) EditText _partySizeText;
    @BindView(R.id.personalContinue) Button _personalContinueButton;

    private String name;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private String numFriends;
    private ArrayList<String> friends;
    private Boolean edit = false;
    private Boolean editSourceLogin = false;

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
        phoneNumber = (String) extras.get("phonenumber");
        lifestyle = (String) extras.get("lifestyle");
        birthYear = (String) extras.get("birthyear");
        partySize = (String) extras.get("partysize");
        allergies = (ArrayList<String>) extras.get("allergies");
        numFriends = (String) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");
        edit = (Boolean) extras.get("edit");
        editSourceLogin = (Boolean) extras.get("editSourceLogin");

        if (birthYear != null) {
            _birthYearText.setText(birthYear);
        }
        if (partySize != null) {
            _partySizeText.setText(partySize);
        }
        if (lifestyle != null) {
            switch (lifestyle) {
                case "Vegan":
                    RadioButton vegan = findViewById(R.id.radioVegan);
                    vegan.setChecked(true);
                    break;
                case "Vegetarian":
                    RadioButton vegetarian = findViewById(R.id.radioVegetarian);
                    vegetarian.setChecked(true);
                    break;
                case "Gluten Free":
                    RadioButton glutenFree = findViewById(R.id.radioGlutenFree);
                    glutenFree.setChecked(true);
                    break;
                case "Carnivore":
                    RadioButton carnivore = findViewById(R.id.radioCarnivore);
                    carnivore.setChecked(true);
                    break;
                case "Pescatarian":
                    RadioButton pescatarian = findViewById(R.id.radioPescatarian);
                    pescatarian.setChecked(true);
                    break;
                case "No Restrictions":
                    RadioButton noRestrictions = findViewById(R.id.radioNoRestrictions);
                    noRestrictions.setChecked(true);
                    break;
            }
        } else {
            RadioButton noRestrictions = findViewById(R.id.radioNoRestrictions);
            noRestrictions.setChecked(true);
            lifestyle = "No Restrictions";
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
                    intent.putExtra("phonenumber", phoneNumber);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("allergies", allergies);
                    intent.putExtra("numFriends", numFriends);
                    intent.putExtra("friends", friends);
                    intent.putExtra("edit", false);
                    intent.putExtra("editSourceLogin", editSourceLogin);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Please Fix Inputs", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (edit == false) {
            setSupportActionBar(_mToolbar2);
            getSupportActionBar().hide();
            setSupportActionBar(_mToolbar);
            _mToolbar.setTitle("Preferences & Lifestyle");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context mContext = getBaseContext();
                    Intent intent = new Intent(mContext, UserNameActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("phonenumber", phoneNumber);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("allergies", allergies);
                    intent.putExtra("numFriends", numFriends);
                    intent.putExtra("friends", friends);
                    intent.putExtra("edit", false);
                    intent.putExtra("editSourceLogin", editSourceLogin);
                    mContext.startActivity(intent);
                }
            });
        } else {
            Log.e("PersonalActivity EDITTT", "pls edit");
            setSupportActionBar(_mToolbar);
            getSupportActionBar().hide();
            setSupportActionBar(_mToolbar2);
            _mToolbar2.setTitle("Edit Age/Party Size/Lifestyle");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

            _personalContinueButton.setVisibility(View.GONE);

            ImageView btn = (ImageView) findViewById(R.id.item_save_edit);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                        Intent intent = new Intent(mContext, ProfileActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("phonenumber", phoneNumber);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("birthyear", birthYear);
                        intent.putExtra("partysize", partySize);
                        intent.putExtra("lifestyle", lifestyle);
                        intent.putExtra("allergies", allergies);
                        intent.putExtra("numFriends", numFriends);
                        intent.putExtra("friends", friends);
                        intent.putExtra("edit", false);
                        intent.putExtra("editSourceLogin", true);
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), "Please Fix Inputs", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
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
            case R.id.radioCarnivore:
                if (checked)
                    // Omnivore chosen
                    lifestyle = "Carnivore";
                    break;
            case R.id.radioPescatarian:
                if (checked)
                    // Pescatarian chosen
                    lifestyle = "Pescatarian";
                    break;
            case R.id.radioNoRestrictions:
                if(checked)
                    // No Restrictions chosen
                    lifestyle = "No Restrictions";
                    break;
        }
    }
}
