package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.BindView;

public class UserNameActivity extends AppCompatActivity {

    @BindView(R.id.usernameInput) EditText _usernameText;
    @BindView(R.id.usernameContinue) Button _usernameContinueButton;

    private String name;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);

        //Get Intent information from sign-up
        Intent recevingIntent = getIntent();
        Bundle extras = recevingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");

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
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
