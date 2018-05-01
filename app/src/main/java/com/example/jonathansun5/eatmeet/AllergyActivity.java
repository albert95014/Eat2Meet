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
import android.widget.CheckBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllergyActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.allergyContinue) Button _allergyContinue;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);
        ButterKnife.bind(this);

        //Get Extras
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

        Log.e("ALLERGIES ARE: ", Arrays.toString(new ArrayList[]{allergies}));

        numFriends = (String) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");

        if (allergies != null) {
            for (String allergy : allergies) {
                Log.e("Auto populate111: ", allergy);
                if (allergy.equals("Eggs")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxEggs)).setChecked(true);
                }
                if (allergy.equals("Dairy")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxDairy)).setChecked(true);
                }
                if (allergy.equals("Pine Nuts")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxPineNuts)).setChecked(true);
                }
                if (allergy.equals("Peanuts")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxPeanuts)).setChecked(true);
                }
                if (allergy.equals("Sesame")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxSesame)).setChecked(true);
                }
                if (allergy.equals("Fish")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxFish)).setChecked(true);
                }
                if (allergy.equals("Gluten")) {
                    Log.e("Auto populate: ", allergy);
                    ((CheckBox) findViewById(R.id.checkboxGluten)).setChecked(true);
                }
                //if (allergy.equals("Others")) {
                //  Log.e("Auto populate: ", allergy);
                //((CheckBox) findViewById(R.id.checkboxOthers)).setChecked(true);
                // }
//                String id = String.format("R.id.checkbox%s", allergy.replace(" ", ""));
//                CheckBox allergicTo = (CheckBox) findViewById(Integer.parseInt(id));
//                allergicTo.setChecked(true);
            }
        } else {
            Log.e("Allergy is empty ", "why???");
            allergies = new ArrayList<>();
        }

        _allergyContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, CircleActivity.class);
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
                mContext.startActivity(intent);
            }
        });

        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("Allergies");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, PersonalActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("phonenumber", phoneNumber);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("birthyear", birthYear);
                intent.putExtra("lifestyle", lifestyle);
                intent.putExtra("partysize", partySize);
                intent.putExtra("allergies", allergies);

                Log.e("Passing from Circle", "allergies is: " + Arrays.toString(new ArrayList[]{allergies}));

                intent.putExtra("numFriends", numFriends);
                intent.putExtra("friends", friends);
                mContext.startActivity(intent);
            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();
        Log.d("Checked", "Added to Allergies");

        // Check which checkbox button was clicked
        switch(view.getId()) {
            case R.id.checkboxEggs:
                if (checked && !allergies.contains("Eggs")) {
                    Log.e("adding to allergies: ", "Eggs");
                    allergies.add("Eggs");
                } else {
                    Log.e("remove from allergies: ", "Eggs");
                    allergies.remove("Eggs");
                }
                break;
            case R.id.checkboxDairy:
                if (checked && !allergies.contains("Dairy")) {
                    Log.e("adding to allergies: ", "Dairy");
                    allergies.add("Dairy");
                } else {
                    Log.e("remove from allergies: ", "Dairy");
                    allergies.remove("Dairy");
                }
                break;
            case R.id.checkboxPineNuts:
                if (checked && !allergies.contains("Pine Nuts")) {
                    Log.e("adding to allergies: ", "Pine Nuts");
                    allergies.add("Pine Nuts");
                } else {
                    Log.e("remove from allergies: ", "Pine Nuts");
                    allergies.remove("Pine Nuts");
                }
                break;
            case R.id.checkboxPeanuts:
                if (checked && !allergies.contains("Peanuts")) {
                    Log.e("adding to allergies: ", "Peanuts");
                    allergies.add("Peanuts");
                } else {
                    Log.e("remove from allergies: ", "Peanuts");
                    allergies.remove("Peanuts");
                }
                break;
            case R.id.checkboxSesame:
                if (checked && !allergies.contains("Sesame")) {
                    Log.e("adding to allergies: ", "Sesame");
                    allergies.add("Sesame");
                } else {
                    Log.e("remove from allergies: ", "Sesame");
                    allergies.remove("Sesame");
                }
                break;
            case R.id.checkboxFish:
                if (checked && !allergies.contains("Fish")) {
                    Log.e("adding to allergies: ", "Fish");
                    allergies.add("Fish");
                } else {
                    Log.e("remove from allergies: ", "Fish");
                    allergies.remove("Fish");
                }
                break;
            case R.id.checkboxGluten:
                if (checked && !allergies.contains("Gluten")) {
                    Log.e("adding to allergies: ", "Gluten");
                    allergies.add("Gluten");
                } else {
                    Log.e("remove from allergies: ", "Gluten");
                    allergies.remove("Gluten");
                }
                break;

        }
    }
}
