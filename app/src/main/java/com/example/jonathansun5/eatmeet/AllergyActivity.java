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

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllergyActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.allergyContinue) Button _allergyContinue;


    private String name;
    private String email;
    private String password;
    private String username;
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

        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("Allergies");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, CircleActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("birthyear", birthYear);
                intent.putExtra("lifestyle", lifestyle);
                intent.putExtra("partysize", partySize);
                intent.putExtra("allergies", allergies);
                intent.putExtra("numFriends", numFriends);
                intent.putExtra("friends", friends);
                mContext.startActivity(intent);
            }
        });

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
        allergies = (ArrayList<String>) extras.get("allergies");
        numFriends = (String) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");

        if (allergies != null) {
            for (String allergy : allergies) {
                String id = String.format("R.id.checkbox%s", allergy.replace(" ", ""));
                CheckBox allergicTo = (CheckBox) findViewById(Integer.parseInt(id));
                allergicTo.setChecked(true);
            }
        } else {
            allergies = new ArrayList<>();
        }

        _allergyContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, CircleActivity.class);
                intent.putExtra("username", username);
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

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();
        Log.d("Checked", "Added to Allergies");

        // Check which checkbox button was clicked
        switch(view.getId()) {
            case R.id.checkboxEggs:
                if (checked)
                    allergies.add("Eggs");
                break;
            case R.id.checkboxDairy:
                if (checked)
                    allergies.add("Dairy");
                break;
            case R.id.checkboxPineNuts:
                if (checked)
                    allergies.add("Pine Nuts");
                break;
            case R.id.checkboxPeanuts:
                if (checked)
                    allergies.add("Peanuts");
                break;
            case R.id.checkboxSesame:
                if (checked)
                    allergies.add("Sesame");
                break;
            case R.id.checkboxFish:
                if (checked)
                    allergies.add("Fish");
                break;
            case R.id.checkboxGluten:
                if (checked)
                    allergies.add("Gluten");
                break;
//            case R.id.checkboxNone:
//                if (checked)
//                    allergies.add("none");
//                break;
            case R.id.checkboxOthers:
                if (checked)
                    allergies.add("Others");
                break;
        }
    }
}
