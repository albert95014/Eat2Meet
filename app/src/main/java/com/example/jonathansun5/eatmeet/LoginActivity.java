package com.example.jonathansun5.eatmeet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        DatabaseReference ref = database.getReference();
                        String userEmail = _emailText.getText().toString().replace(".", ",");

                        ref.child("users").child(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    String userPassword = _passwordText.getText().toString();
                                    if (userPassword.equals(dataSnapshot.child("password").getValue(String.class))) {
                                        // email and password match
                                        String yayemail = _emailText.getText().toString().replace(".", ",");
                                        String yayusername = dataSnapshot.child("username").getValue(String.class);
                                        String yayname = dataSnapshot.child("name").getValue(String.class);
                                        String yaypassword = dataSnapshot.child("password").getValue(String.class);
                                        String yaybirthYear = dataSnapshot.child("birthYear").getValue(String.class);
                                        String yaylifestyle = dataSnapshot.child("lifestyle").getValue(String.class);
                                        String yaypartysize = dataSnapshot.child("partySize").getValue(String.class);
                                        String yayphonenumber = dataSnapshot.child("phoneNumber").getValue(String.class);
                                        String yaynumFriends = dataSnapshot.child("numFriends").getValue(String.class);

                                        ArrayList<String> yayallergies = new ArrayList<String>();
                                        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                                        yayallergies = dataSnapshot.child("allergies").getValue(t);

                                        ArrayList<String> yayFriends = new ArrayList<String>();
                                        GenericTypeIndicator<ArrayList<String>> s = new GenericTypeIndicator<ArrayList<String>>() {};
                                        yayFriends = dataSnapshot.child("friends").getValue(s);
                                        progressDialog.dismiss();

                                        onLoginSuccess(yayname, yayemail, yaypassword, yayusername,
                                                yaylifestyle, yaypartysize, yaybirthYear, yayphonenumber, yayallergies, yaynumFriends, yayFriends);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                                        onLoginFailed();
                                    }
                                    // use "username" already exists
                                    // Let the user know he needs to pick another username.

                                } else {
                                    // User does not exist. NOW call createUserWithEmailAndPassword
//                                    mAuth.createUserWithPassword(...);
                                    // Your previous code here.
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Email is incorrect", Toast.LENGTH_SHORT).show();
                                    onLoginFailed();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String name, String email, String password, String username,
                               String lifestyle, String partySize, String birthYear,
                               String phonenumber, ArrayList<String> allergies, String numFriends, ArrayList<String> friends) {
        _loginButton.setEnabled(true);
        Context mContext = getBaseContext();
        Intent intent = new Intent(mContext, ProfileActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("username", username);
        intent.putExtra("phonenumber", phonenumber);
        intent.putExtra("lifestyle", lifestyle);
        intent.putExtra("birthyear", birthYear);
        intent.putExtra("partysize", partySize);
        intent.putExtra("allergies", allergies);
        intent.putExtra("numFriends", numFriends);
        intent.putExtra("friends", friends);
        intent.putExtra("editSourceLogin", true);
        mContext.startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        Context mContext = getBaseContext();
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        _loginButton.setEnabled(true);

    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Please enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
