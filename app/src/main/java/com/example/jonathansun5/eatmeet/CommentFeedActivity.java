package com.example.jonathansun5.eatmeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Date;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

// Displays a list of comments for a particular landmark.
public class CommentFeedActivity extends AppCompatActivity {

    private static final String TAG = CommentFeedActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Comment> mComments = new ArrayList<Comment>();
    private String mUsername;
    private DatabaseReference mMessageRef;

    // UI elements
    EditText commentInputBox;
    RelativeLayout layout;
    Button sendButton;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_feed);

        Intent goToCommentActivityIntent = getIntent();
        Bundle intentExtras = goToCommentActivityIntent.getExtras();

        String chatName = "Messaging Board";
        mUsername = "160 student";
        if(intentExtras!=null) {
            mUsername =(String) intentExtras.get("username");
        }

        // sets the app bar's title
        this.setTitle(chatName);

        // hook up UI elements
        layout = findViewById(R.id.comment_layout);
        commentInputBox = layout.findViewById(R.id.comment_input_edit_text);
        sendButton = layout.findViewById(R.id.send_button);

        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(chatName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SetLocationActivity.class);
                intent.putExtra("username", mUsername);
                v.getContext().startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.comment_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an onclick for the send button
        setOnClickForSendButton();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference messagesRef = database.getReference("messageboards");
        mMessageRef = messagesRef.child(chatName);

        // Attach a listener to read the data at our posts reference
        mMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                pullDatabaseComments(snapshot);
            }

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                pullDatabaseComments(snapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                pullDatabaseComments(snapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                pullDatabaseComments(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void pullDatabaseComments(DataSnapshot data) {
        mComments.add(data.getValue(Comment.class));
        setAdapterAndUpdateData();
    }

    private void setOnClickForSendButton() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInputBox.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    // don't do anything if nothing was added
                    commentInputBox.requestFocus();
                } else {
                    // clear edit text, post comment
                    commentInputBox.setText("");
                    postNewComment(comment);
                }
            }
        });
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new CommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mAdapter);

        // scroll to the last comment
        if (mComments.size() > 0) {
            mRecyclerView.smoothScrollToPosition(mComments.size() - 1);
        } else {
            mRecyclerView.smoothScrollToPosition(mComments.size());
        }

    }

    private void postNewComment(String commentText) {
        Comment newComment = new Comment(commentText, mUsername, new Date().getTime());
        DatabaseReference comment = mMessageRef.push();
        comment.setValue(newComment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
