package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;

public class CircleActivity extends AppCompatActivity {

    private String name;
    private String email;
    private String password;
    private String username;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private ArrayList<String> friends;
    private String circleText;
    private int numFriends;

    private Button circleAdd;
    private Button circleContinue;
    private EditText circleEditText;


    //@BindView(R.id.circleAdd) Button circleAdd;
    //@BindView(R.id.circleContinue) Button circleContinue;
    //@BindView(R.id.circleEditText) EditText circleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

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
        numFriends = 0;
        friends = new ArrayList<>();

        circleAdd = (Button) findViewById(R.id.circleAdd);
        circleContinue = (Button) findViewById(R.id.circleContinue);
        circleEditText = (EditText) findViewById(R.id.circleEditText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();


        circleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleText = circleEditText.getText().toString().replace(".", ",");
                ref.child("users").child(circleText).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            numFriends += 1;
                            friends.add(circleText);
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

        circleContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, PersonalActivity.class);
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
}
