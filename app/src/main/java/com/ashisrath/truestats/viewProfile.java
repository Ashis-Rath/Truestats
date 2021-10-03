package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewProfile extends AppCompatActivity {
    ImageView backbtnViewProfile;
    Button backBtnViewProfie;
    LinearLayout EditName, EditNumber;
    TextView UserName, UserNumber, UserEmail, UserUID;
    String user_id, User_Email;
    ProgressBar progressBar7;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        // Block User Touch Events
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        progressBar7 = findViewById(R.id.progressBar7);
        progressBar7.setVisibility(View.VISIBLE);


        // Fetching Data from Home Page
        user_id = getIntent().getStringExtra("UserKaID");


//        // UserUID
        FirebaseAuth auth = FirebaseAuth.getInstance();
//        user_id = Auth.getUid();
//        assert user_id != null;
        // User Email
        FirebaseUser AuthUser = auth.getCurrentUser();
        assert AuthUser != null;
        User_Email = AuthUser.getEmail();


        // Initialising
        UserName = findViewById(R.id.UserName);
        UserNumber = findViewById(R.id.UserNumber);
        UserEmail = findViewById(R.id.UserEmail);
        UserUID = findViewById(R.id.UserUID);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameOfUser = String.valueOf(snapshot.child("Name").getValue());
                String numberOfUser = String.valueOf(snapshot.child("Phone_number").getValue());

                UserName.setText(nameOfUser);
                UserNumber.setText(numberOfUser);
                UserEmail.setText(User_Email);
                UserUID.setText(user_id);

                progressBar7.setVisibility(View.INVISIBLE);
                // Allow User Touch Events
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Clicking on Back Arrow
        backbtnViewProfile = findViewById(R.id.backbtnViewProfile);
        backbtnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Clicking on Back Button
        backBtnViewProfie = findViewById(R.id.backBtnViewProfie);
        backBtnViewProfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Clicking on Edit Name
        EditName = findViewById(R.id.EditName);
        EditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewProfile.this, editName.class);
                intent.putExtra("uuid", user_id);
                startActivity(intent);
            }
        });

        // Clicking on Edit Number
        EditNumber = findViewById(R.id.EditNumber);
        EditNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewProfile.this, editNumber.class);
                intent.putExtra("uuid", user_id);
                startActivity(intent);
            }
        });






    }
}