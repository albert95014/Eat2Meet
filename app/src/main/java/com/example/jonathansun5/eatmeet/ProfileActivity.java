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

//    private android.support.v7.widget.Toolbar _mToolbar;

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.nameEditText) TextView _nameText;
    @BindView(R.id.emailEditText) TextView _emailText;
    @BindView(R.id.passwordEditText) TextView _passwordText;
    @BindView(R.id.usernameEditText) TextView _usernameText;
    @BindView(R.id.lifestyleEditText) TextView _lifestyleText;
    @BindView(R.id.birthyearEditText) TextView _birthyearText;
    @BindView(R.id.partysizeEditText) TextView _partysizeText;
    @BindView(R.id.allergiesEditText) TextView _allergiesText;
    @BindView(R.id.numFriendsText) TextView _numFriendsText;
    @BindView(R.id.toMap) Button _toMapButton;

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

    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);


        setSupportActionBar(_mToolbar);
        _mToolbar.setTitle("User Profile");
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



        //Link up UI elements


        _toMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startIntent(getBaseContext(), SetLocationActivity.class);
            }
        });

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
        numFriends = (String) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");

        //Set profile information
        _usernameText.setText("@" + username);
        _nameText.setText(name);
        _emailText.setText(email);
        _passwordText.setText(password);
        _birthyearText.setText(birthYear);
        _lifestyleText.setText(lifestyle);
        _partysizeText.setText(partySize);
        _numFriendsText.setText(numFriends);
        String allergyFull = "Allergies: ";
        for (int i = 0; i < allergies.size(); i++){
            if (i != allergies.size() - 1) {
                allergyFull = allergyFull + allergies.get(i) + ", ";
            } else {
                allergyFull = allergyFull + allergies.get(i);
            }
        }
        _allergiesText.setText(allergyFull);



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
//                startIntent(mContext, SignupActivity.class);
//            }
//        });
//
//        _editPasswordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Signup activity
//                Context mContext = getBaseContext();
//                startIntent(mContext, SignupActivity.class);
//            }
//        });
//
//        _editBirthYearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                startIntent(mContext, PersonalActivity.class);
//            }
//        });
//
//        _editLifestyleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                startIntent(mContext, PersonalActivity.class);
//            }
//        });
//
//        _editPartySizeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Personal activity
//                Context mContext = getBaseContext();
//                startIntent(mContext, PersonalActivity.class);
//            }
//        });
//
//        _editAllergiesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the Allergy activity
//                Context mContext = getBaseContext();
//                startIntent(mContext, AllergyActivity.class);
//
//            }
//        });
    }

    public void startIntent(Context mContext, Class c) {
        Intent intent = new Intent(mContext, c);
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
}