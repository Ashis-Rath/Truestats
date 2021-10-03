package com.ashisrath.truestats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editNumber extends AppCompatActivity {
    EditText editUserNumber;
    Button editUserNumberCancelBtn, editUserNumberUpdateBtn;
    String user_id, newNumberOfUser;
    ProgressBar progressBar9;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);

        // Fetching Data from Settings
        user_id = getIntent().getStringExtra("uuid");

        // Database Stuff
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        // Initialising
        editUserNumber = findViewById(R.id.editUserNumber);
        progressBar9 = findViewById(R.id.progressBar9);

        // Cancel Button
        editUserNumberCancelBtn = findViewById(R.id.editUserNumberCancelBtn);
        editUserNumberCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Update Button
        editUserNumberUpdateBtn = findViewById(R.id.editUserNumberUpdateBtn);
        editUserNumberUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateDataEditNumber();
            }
        });
    }

    private void ValidateDataEditNumber() {
        // Getting the Input as Strings from the Edit Texts Above
        newNumberOfUser = editUserNumber.getText().toString();

        if (newNumberOfUser.isEmpty()){
            editUserNumber.setError("Please enter your Number.");
            editUserNumber.requestFocus();
        } else {
            updateNumberofUser();
        }
    }

    private void updateNumberofUser() {
        progressBar9.setVisibility(View.VISIBLE);
        databaseReference.child(user_id).child("Phone_number").setValue(newNumberOfUser);
        finish();
    }
}