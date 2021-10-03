package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    Button signinBtn;
    TextView registerLoginPageBtn, btnforgotpassword;
    EditText signinEmailET, signinPasswordET;
    ProgressBar progressBar;

    private String logEmail, logPassword;

    // Firebase
    private FirebaseAuth Auth;
    // Firebase

    // SharedPreferences 2nd Trial
    SharedPreferences sharedpreferences;
    int autoSave;
    // SharedPreferences 2nd Trial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialising
        signinEmailET = findViewById(R.id.signinEmailET);
        signinPasswordET = findViewById(R.id.signinPasswordET);
        progressBar = findViewById(R.id.progressBar);

        // Initialising Firebase Auth
        Auth = FirebaseAuth.getInstance();

        //******************************************************************************************
        // SharedPreferences 2nd Trial
        //"autoLogin" is a unique string to identify the instance of this shared preference
        sharedpreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int j = sharedpreferences.getInt("key", 0);
        //Default is 0 so autologin is disabled
        if(j > 0){
            Intent activity = new Intent(getApplicationContext(), HomePage.class);
            startActivity(activity);
        }
        // SharedPreferences 2nd Trial
        //******************************************************************************************

        btnforgotpassword = findViewById(R.id.btnforgotpassword);
        btnforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                // No finish method: On Clicking Back Button, Return to Login
//                finish();

            }
        });


        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                finish();
            }
        });


        registerLoginPageBtn = findViewById(R.id.signinRegisterPageBtn);
        registerLoginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });




        signinBtn = findViewById(R.id.signinBtn);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Syntax to Hide Virtual Keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(signinBtn.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                // Syntax to Hide Virtual Keyboard

                validateUserData();
            }


        });
    }


    // Method to Check all fields are filled.
    private void validateUserData() {
        logEmail = signinEmailET.getText().toString();
        logPassword = signinPasswordET.getText().toString();

        if (logEmail.isEmpty()){
            signinEmailET.setError("Please enter your Email Address.");
            signinEmailET.requestFocus();
        } else if(logPassword.isEmpty()){
            signinPasswordET.setError("Please fill in your Password.");
            signinPasswordET.requestFocus();
        } else {
            loginUser();
        }


    }

    // Method to Sign in User
    private void loginUser() {

        progressBar.setVisibility(View.VISIBLE);
        Auth.signInWithEmailAndPassword(logEmail, logPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);

                            //*****************************Keep User Logged In*************************************************************
                            // SharedPreferences 2nd Trial
                            //Once you click login, it will add 1 to shredPreference which will allow autologin in onCreate
                            autoSave = 1;
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("key", autoSave);
                            editor.apply();
                            // SharedPreferences 2nd Trial
                            //*****************************Keep User Logged In*************************************************************

                            openMain();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Error :"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error :"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Method to go to Home Page after Sign in
    private void openMain(){
        startActivity(new Intent(LoginActivity.this, HomePage.class));
        finish();

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();

        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }
}