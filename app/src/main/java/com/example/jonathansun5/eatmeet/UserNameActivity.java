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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class UserNameActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;

    @BindView(R.id.usernameInput) EditText _usernameText;
    @BindView(R.id.usernameContinue) Button _usernameContinueButton;

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
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Create a Username");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, SignupActivity.class);
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

        //Get Intent information from sign-up
        Intent recevingIntent = getIntent();
        Bundle extras = recevingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");
        username = (String) extras.get("username");
        lifestyle = (String) extras.get("lifestyle");
        birthYear = (String) extras.get("birthyear");
        partySize = (String) extras.get("partysize");
        allergies = (ArrayList<String>) extras.get("allergies");

        if (username != null) {
            _usernameText.setText(username);
        }

        _usernameContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = _usernameText.getText().toString();
                if (username.isEmpty() || username.length() < 3) {
                    _usernameText.setError("at least 3 characters");
                } else {
                    _usernameText.setError(null);
                    Context mContext = getBaseContext();
                    Intent intent = new Intent(mContext, PersonalActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("allergies", allergies);
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
