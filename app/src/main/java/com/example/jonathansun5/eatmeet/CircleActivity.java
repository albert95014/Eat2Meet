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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.circleAdd) Button _circleAdd;
    @BindView(R.id.circleContinue) Button _circleContinue;
    @BindView(R.id.circleEditText) EditText _circleEditText;
    @BindView(R.id.currentlyAdded) TextView _currentFriends;

    private String name;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private ArrayList<String> friends;
    private String circleText;
    private String numFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
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
        if (numFriends == null) {
            numFriends = String.valueOf(0);
        }
        if (friends == null) {
            friends = new ArrayList<>();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();


        _circleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String temp = _circleEditText.getText().toString();
                circleText = _circleEditText.getText().toString().replace(".", ",");
                ref.child("users").child(circleText).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Integer numberOfFriends = Integer.parseInt(numFriends);
                            numberOfFriends += 1;
                            numFriends = String.valueOf(numberOfFriends);
                            if (duplicateChecker(temp) == false){
                                friends.add(temp);
                            }
                            _currentFriends.setText(listToString(friends));
                            Toast.makeText(CircleActivity.this, "User Added!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(CircleActivity.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        _circleContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                mContext.startActivity(intent);
            }
        });

        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("Add Friends");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, AllergyActivity.class);
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
                mContext.startActivity(intent);
            }
        });
    }

    public String listToString(ArrayList<String> listFriends){
        String finalString = "";
        for (int i = 0; i < listFriends.size(); i++){
            finalString += listFriends.get(i) + ", ";
        }
        return finalString;
    }

    public Boolean duplicateChecker(String name){
        for (int i = 0; i < friends.size(); i++){
            if (name.equals(friends.get(i))){
                return true;
            }
        }
        return false;
    }
}
