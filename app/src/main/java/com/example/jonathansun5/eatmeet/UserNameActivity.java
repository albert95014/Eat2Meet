package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserNameActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button usernameCont;
    private String name;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);

        //Get UI elements
        usernameText = (EditText) findViewById(R.id.usernameInput);
        usernameCont = (Button) findViewById(R.id.usernameContinue);

        //Get Intent information from sign-up
        Intent recevingIntent = getIntent();
        Bundle extras = recevingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");


        usernameCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, PersonalActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                mContext.startActivity(intent);

            }
        });
    }
}
