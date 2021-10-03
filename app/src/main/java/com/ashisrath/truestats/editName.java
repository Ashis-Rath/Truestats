package com.ashisrath.truestats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editName extends AppCompatActivity {
    EditText editUserNameET;
    Button editUserNameUpdateBtn, editUserNameCancelBtn;
    String user_id, newNameOfUser;
    ProgressBar progressBar8;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        // Fetching Data from Settings
        user_id = getIntent().getStringExtra("uuid");

        // Database Stuff
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        // Initialising
        editUserNameET = findViewById(R.id.editUserNameET);
        progressBar8 = findViewById(R.id.progressBar8);
        


        // Cancel Button
        editUserNameCancelBtn = findViewById(R.id.editUserNameCancelBtn);
        editUserNameCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Update Button
        editUserNameUpdateBtn = findViewById(R.id.editUserNameUpdateBtn);
        editUserNameUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateDataEditName();
            }
        });


    }

    private void ValidateDataEditName() {
        // Getting the Input as Strings from the Edit Texts Above
        newNameOfUser = editUserNameET.getText().toString();

        if (newNameOfUser.isEmpty()){
            editUserNameET.setError("Please enter your Name.");
            editUserNameET.requestFocus();
        } else {
            updateNameofOrg();
        }
    }

    private void updateNameofOrg() {
        progressBar8.setVisibility(View.VISIBLE);
        databaseReference.child(user_id).child("Name").setValue(newNameOfUser);
        finish();
    }
}