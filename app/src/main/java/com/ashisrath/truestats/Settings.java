package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    TextView  cityUserSettingsTV, stateUserSettingsTV;
    String userUniqueID;
    Button editLocationBtn, deleteAccountBTN;
    ImageView backBtnTV;
    
    FirebaseUser firebaseUser;
    DatabaseReference dbRef;

    //*****************************Keep User Logged In*************************************************************
    // SharedPreferences 2nd Trial
    SharedPreferences sharedPreferences;
    // SharedPreferences 2nd Trial
    //*****************************Keep User Logged In*************************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Fetching Data from Home Page
        userUniqueID = getIntent().getStringExtra("UserKaID");


        // Initialising TextView
        cityUserSettingsTV = findViewById(R.id.cityUserSettingsTV);
        stateUserSettingsTV = findViewById(R.id.stateUserSettingsTV);
        deleteAccountBTN = findViewById(R.id.deleteAccountBTN);

        // Database Stuff
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //*****************************Keep User Logged In*************************************************************
        // SharedPreferences 2nd Trial
        //Get that instance saved in the previous activity
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        // SharedPreferences 2nd Trial
        //*****************************Keep User Logged In*************************************************************


        // Getting City & State details from Database
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users");
        database.child(userUniqueID).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cityOfUser = String.valueOf(snapshot.child("City").getValue());
                String stateOfUser = String.valueOf(snapshot.child("State").getValue());

                cityUserSettingsTV.setText("City: " + cityOfUser + " ");
                stateUserSettingsTV.setText("State: " + stateOfUser + " ");


                // Progress Bar Visibility
//                progressBarBedStatistics.setVisibility(View.INVISIBLE);
                // Allow User Touch Events
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Clicking on Back Button
        backBtnTV = findViewById(R.id.backBtnTV);
        backBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Clicking on Edit Location
        editLocationBtn = findViewById(R.id.editLocationBtn);
        editLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, editLocation.class);
                intent.putExtra("UserKaIDAgain", userUniqueID);
                startActivity(intent);
            }
        });

        // Clicking on Delete Account
        deleteAccountBTN = findViewById(R.id.deleteAccountBTN);
        deleteAccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Settings.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in the complete removal of your data from the system which won't let you access this app in future.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser();
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Settings.this, "Your Account has now been Deleted!!", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().signOut();

                                    //*****************************Keep User Logged In*************************************************************
                                    // SharedPreferences 2nd Trial
                                    //Resetting value to 0 so autologin is disabled
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("key", 0);
                                    editor.apply();
                                    // SharedPreferences 2nd Trial
                                    //*****************************Keep User Logged In*************************************************************

                                    Intent intent = new Intent(Settings.this, LoginActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Settings.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });


    }

    private void deleteUser() {
        dbRef.child(userUniqueID).removeValue();

    }
}