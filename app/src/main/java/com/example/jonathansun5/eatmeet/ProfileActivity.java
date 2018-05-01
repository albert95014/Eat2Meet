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

        import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

//    private android.support.v7.widget.Toolbar _mToolbar;

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.nameEditText) TextView _nameText;
    @BindView(R.id.emailEditText) TextView _emailText;
    @BindView(R.id.passwordEditText) TextView _passwordText;
    @BindView(R.id.usernameEditText) TextView _usernameText;
    @BindView(R.id.phoneEditText) TextView _phonenumberText;
    @BindView(R.id.lifestyleEditText) TextView _lifestyleText;
    @BindView(R.id.birthyearEditText) TextView _birthyearText;
    @BindView(R.id.partysizeEditText) TextView _partysizeText;
    @BindView(R.id.allergiesEditText) TextView _allergiesText;
    @BindView(R.id.friendsEditText) TextView _friendsText;
    @BindView(R.id.numFriendsText) TextView _numFriendsText;
    @BindView(R.id.toMap) Button _toMapButton;

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

    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        //Get Intent information
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

        String censor = "";
        for (int i = 0; i < password.length() - 3; i++) {
            censor += "*";
        }

        String last3Characters = password.substring(password.length() - 3);

        //Set profile information
        _usernameText.setText("@" + username);
        _phonenumberText.setText(phoneNumber);
        _nameText.setText(name);
        _emailText.setText(email);
        _passwordText.setText(censor + last3Characters);
        _birthyearText.setText(birthYear);
        _lifestyleText.setText(lifestyle);
        _partysizeText.setText(partySize);
        _numFriendsText.setText(numFriends);
        String allergyFull = "Allergies: ";
        if (allergies.size() == 0) {
            _allergiesText.setText(allergyFull + "None");
        } else {
            for (int i = 0; i < allergies.size(); i++){
                if (i != allergies.size() - 1) {
                    allergyFull = allergyFull + allergies.get(i) + ", ";
                } else {
                    allergyFull = allergyFull + allergies.get(i);
                }
            }
            _allergiesText.setText(allergyFull);
        }
        String friendsTextGood = "Friends: ";
        if (friends.size() == 0) {
            _friendsText.setText(friendsTextGood + "None");
        } else if (friends.size() == 1) {
            friendsTextGood = friends.get(0).replace(",", ".");
        } else {
            for (int i = 0; i < friends.size(); i++){
                if (i == friends.size() - 1) {
                    friendsTextGood += friends.get(i).replace(",", ".");
                } else {
                    friendsTextGood += friends.get(i).replace(",", ".") + ", ";
                }
            }
        }
        _friendsText.setText(friendsTextGood);

        _toMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Firebase and next activity
                DatabaseReference users = database.getReference("users");
                DatabaseReference userEmail = users.child(email.replace(".", ","));
                userEmail.child("username").setValue(username);
                userEmail.child("phoneNumber").setValue(phoneNumber);
                userEmail.child("name").setValue(name);
                userEmail.child("password").setValue(password);
                userEmail.child("birthYear").setValue(birthYear);
                userEmail.child("lifestyle").setValue(lifestyle);
                userEmail.child("partySize").setValue(partySize);
                userEmail.child("allergies").setValue(allergies);
                userEmail.child("numFriends").setValue(numFriends);
                userEmail.child("friends").setValue(friends);

                Context mContext = getBaseContext();
                Intent intent = new Intent(mContext, SetLocationActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
        });

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
}