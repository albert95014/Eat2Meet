package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;

    private TextView nameText;
    private TextView emailText;
    private TextView passwordText;
    private TextView usernameText;
    private TextView lifestyleText;
    private TextView birthyearText;
    private TextView partysizeText;
    private TextView allergiesText;
    private TextView numFriendsText;

    private String name;
    private String email;
    private String password;
    private String username;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private int numFriends;
    private ArrayList<String> friends;

    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);


        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("User Profile");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, searchingmap.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("birthyear", birthYear);
                intent.putExtra("lifestyle", lifestyle);
                intent.putExtra("partysize", partySize);
                intent.putExtra("allergies", allergies);
                mContext.startActivity(intent);
            }
        });

        //Link up UI elements
        nameText = (TextView) findViewById(R.id.nameEditText);
        emailText = (TextView) findViewById(R.id.emailEditText);
        passwordText = (TextView) findViewById(R.id.passwordEditText);
        usernameText = (TextView) findViewById(R.id.usernameEditText);
        lifestyleText = (TextView) findViewById(R.id.lifestyleEditText);
        birthyearText = (TextView) findViewById(R.id.birthyearEditText);
        partysizeText = (TextView) findViewById(R.id.partysizeEditText);
        allergiesText = (TextView) findViewById(R.id.allergiesEditText);
        numFriendsText = (TextView) findViewById(R.id.numFriendsText);

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
        numFriends = (int) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");

        //Set profile information
        usernameText.setText("@" + username);
        nameText.setText(name);
        emailText.setText(email);
        passwordText.setText(password);
        birthyearText.setText(birthYear);
        lifestyleText.setText(lifestyle);
        partysizeText.setText(partySize);
        numFriendsText.setText(String.valueOf(numFriends));
        String allergyFull = "Allergies: ";
        for (int i = 0; i < allergies.size(); i++){
            if (i != allergies.size() - 1) {
                allergyFull = allergyFull + allergies.get(i) + ", ";
            } else {
                allergyFull = allergyFull + allergies.get(i);
            }
        }
        allergiesText.setText(allergyFull);



//        _saveProfileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Firebase and next activity
//                DatabaseReference users = database.getReference("users");
//                DatabaseReference userEmail = users.child(email.replace(".", ","));
//                userEmail.child("username").setValue(username);
//                userEmail.child("name").setValue(name);
//                userEmail.child("password").setValue(password);
//                userEmail.child("birthYear").setValue(birthYear);
//                userEmail.child("lifestyle").setValue(lifestyle);
//                userEmail.child("partySize").setValue(partySize);
//                userEmail.child("allergies").setValue(allergies);
//            }
//        });
//
//        _editEmailButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Signup activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, SignupActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
//
//        _editPasswordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Signup activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, SignupActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
//
//        _editBirthYearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, PersonalActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
//
//        _editLifestyleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, PersonalActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
//
//        _editPartySizeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, PersonalActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
//
//        _editAllergiesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Allergy activity
//                Context mContext = getBaseContext();
//                Intent intent = new Intent(mContext, AllergyActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("name", name);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("birthyear", birthYear);
//                intent.putExtra("lifestyle", lifestyle);
//                intent.putExtra("partysize", partySize);
//                intent.putExtra("allergies", allergies);
//                mContext.startActivity(intent);
//            }
//        });
    }
}
