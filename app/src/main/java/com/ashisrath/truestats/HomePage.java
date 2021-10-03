package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
    ImageView popUpMenu, hospitalBedsIcon, oxygenStockIcon, covidBedsIcon, settingsIcon, viewProfileIcon, manualicon, aboutUsIcon;
    TextView userNameTV;
    String user_id, userName, userCity, userState;
    ProgressBar progressBar6;


    // Firebase
    private FirebaseAuth Auth;
    // Firebase

    //*****************************Keep User Logged In*************************************************************
    // SharedPreferences 2nd Trial
    SharedPreferences sharedPreferences;
    // SharedPreferences 2nd Trial
    //*****************************Keep User Logged In*************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        // Block User Touch Events
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        progressBar6 = findViewById(R.id.progressBar6);
        progressBar6.setVisibility(View.VISIBLE);



        // Initialising ImageView & TextView
        userNameTV = findViewById(R.id.userNameTV);



        // Initialising Firebase Auth
        Auth = FirebaseAuth.getInstance();
        // Initialising Firebase Auth

        //*****************************Keep User Logged In*************************************************************
        // SharedPreferences 2nd Trial
        //Get that instance saved in the previous activity
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        // SharedPreferences 2nd Trial
        //*****************************Keep User Logged In*************************************************************

        // Getting User UID
        user_id = Auth.getUid();
//        assert user_id != null;

        // Checking & Fetching Info of User
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName = String.valueOf(snapshot.child("Name").getValue());
                userCity = String.valueOf(snapshot.child("City").getValue());
                userState = String.valueOf(snapshot.child("State").getValue());
                userNameTV.setText("Hi " + userName + "!");


                progressBar6.setVisibility(View.INVISIBLE);
                // Allow User Touch Events
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Popup Menu
        popUpMenu = findViewById(R.id.popUpMenu);
        popUpMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Three-Dot Menu
                PopupMenu popup = new PopupMenu(HomePage.this, v);
                popup.setOnMenuItemClickListener(HomePage.this::onOptionsItemSelected);
                popup.inflate(R.menu.main_menu);
                popup.show();
                // Three-Dot Menu

            }

        });
        // Popup Menu

        // Clicking on Hospital Beds
        hospitalBedsIcon = findViewById(R.id.hospitalBedsIcon);
        hospitalBedsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, HospitalBedRecyclerView.class);
                intent.putExtra("cityOfUser", userCity);
                intent.putExtra("stateOfUser", userState);
                startActivity(intent);
            }
        });

        // Clicking on Oxygen Stock
        oxygenStockIcon = findViewById(R.id.oxygenStockIcon);
        oxygenStockIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, OxygenDbToRecyclerView.class);
                intent.putExtra("cityOfUser", userCity);
                intent.putExtra("stateOfUser", userState);
                startActivity(intent);
            }
        });

        // Clicking on COVID Hospital
        covidBedsIcon = findViewById(R.id.covidBedsIcon);
        covidBedsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, CovidHospitalRecyclerView.class);
                intent.putExtra("cityOfUser", userCity);
                intent.putExtra("stateOfUser", userState);
                startActivity(intent);
            }
        });

        // Clicking on Settings Icon
        settingsIcon = findViewById(R.id.settingsIcon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Settings.class);
                intent.putExtra("UserKaID", user_id);
                startActivity(intent);
            }
        });

        // Clicking on View Profile
        viewProfileIcon = findViewById(R.id.viewProfileIcon);
        viewProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, viewProfile.class);
                intent.putExtra("UserKaID", user_id);
                startActivity(intent);
            }
        });

        // Clicking on About Us
        aboutUsIcon = findViewById(R.id.aboutUsIcon);
        aboutUsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, about_us_activity.class);
                startActivity(intent);
            }
        });

        // Clicking on Manual
        manualicon = findViewById(R.id.manualicon);
        manualicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, manual.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signoutmenu:
                FirebaseAuth.getInstance().signOut();

                //*****************************Keep User Logged In*************************************************************
                // SharedPreferences 2nd Trial
                //Resetting value to 0 so autologin is disabled
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("key", 0);
                editor.apply();
                // SharedPreferences 2nd Trial
                //*****************************Keep User Logged In*************************************************************

                Intent intent = new Intent(HomePage.this, LoginActivity.class);
                startActivity(intent);
//                finish();
                break;
            default:
//                return false;
                break;

        }
        return true;
    }

    // Back Button Closes the App
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();

        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }


}