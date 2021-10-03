package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView imageView;
    ImageView calendarIcon;
    EditText userNameET, userPhoneNumberET, userEmailET, userPasswordET;
//    EditText dateOfBirth;
    TextView signinRegisterPageBtn;
//    private int mDate, mMonth, mYear;
//    Spinner genderSpinner, bloodGroupSpinner;
    Spinner stateSpinner, citySpinner;
    Button signupBtnSignupActivity;
    ProgressBar progressBar2;


    String name, dob, gender, bloodGroup, phoneNumber, state, city, Email, Password;

//    ArrayAdapter<String> adapterCity;
    ArrayAdapter<CharSequence> adapterCity;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Initialising EditText & Button
        userNameET = findViewById(R.id.userNameET);
        userPhoneNumberET = findViewById(R.id.userPhoneNumberET);
        userEmailET = findViewById(R.id.userEmailET);
        userPasswordET = findViewById(R.id.userPasswordET);
        progressBar2 = findViewById(R.id.progressBar2);


        // Back Button
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signinRegisterPageBtn = findViewById(R.id.signinRegisterPageBtn);
        signinRegisterPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        //Date of Birth Picker
//        dateOfBirth = findViewById(R.id.dateOfBirth);
//        calendarIcon = findViewById(R.id.calendarIcon);
//        dateOfBirth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                mDate = calendar.get(Calendar.DATE);
//                mMonth = calendar.get(Calendar.MONTH);
//                mYear = calendar.get(Calendar.YEAR);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        if (month >= 0) {
//                            month = month +1 ;
//                        }
//                        dateOfBirth.setText(dayOfMonth + "/" + month + "/" + year);
//                    }
//                }, mYear, mMonth, mDate);
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                datePickerDialog.show();
//            }
//        });

//        calendarIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                mDate = calendar.get(Calendar.DATE);
//                mMonth = calendar.get(Calendar.MONTH);
//                mYear = calendar.get(Calendar.YEAR);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        if (month >= 0) {
//                            month = month +1 ;
//                        }
//                        dateOfBirth.setText(dayOfMonth + "/" + month + "/" + year);
//                    }
//                }, mYear, mMonth, mDate);
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                datePickerDialog.show();
//            }
//        });

//        // Gender Spinner
//        genderSpinner = findViewById(R.id.genderSpinner);
//        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
//        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        genderSpinner.setAdapter(adapterGender);
//        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (parent.getItemAtPosition(position).equals("Select Gender")){
//                    // do nothing
//                }
//                else {
//                    // On Selecting a Spinner Item
//                    String genderItem = parent.getItemAtPosition(position).toString();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        // Blood Group Spinner
//        bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
//        ArrayAdapter<CharSequence> adapterBloodGroup = ArrayAdapter.createFromResource(this, R.array.blood_group, android.R.layout.simple_spinner_item);
//        adapterBloodGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        bloodGroupSpinner.setAdapter(adapterBloodGroup);
//        bloodGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (parent.getItemAtPosition(position).equals("Blood Group")){
//                    // do nothing
//                }
//                else {
//                    // On Selecting a Spinner Item
//                    String bloodGroupItem = parent.getItemAtPosition(position).toString();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        //State Spinner
        stateSpinner = findViewById(R.id.stateSpinnerUpdate);
        ArrayAdapter<CharSequence> adapterState = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapterState);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //From Here
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.YourCity, android.R.layout.simple_spinner_item);
                }
                if (position==1){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.AndamanandNicobar, android.R.layout.simple_spinner_item);
                }
                if (position==2){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.AndhraPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==3){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ArunachalPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==4){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Assam, android.R.layout.simple_spinner_item);
                }
                if (position==5){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Bihar, android.R.layout.simple_spinner_item);
                }
                if (position==6){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Chandigarh, android.R.layout.simple_spinner_item);
                }
                if (position==7){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Chattisgarh, android.R.layout.simple_spinner_item);
                }
                if (position==8){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.DadraandNagarHaveli, android.R.layout.simple_spinner_item);
                }
                if (position==9){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Damananddiu, android.R.layout.simple_spinner_item);
                }
                if (position==10){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Delhi, android.R.layout.simple_spinner_item);
                }
                if (position==11){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Goa, android.R.layout.simple_spinner_item);
                }
                if (position==12){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Gujurat, android.R.layout.simple_spinner_item);
                }
                if (position==13){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Haryana, android.R.layout.simple_spinner_item);
                }
                if (position==14){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.HimachalPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==15){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.JammuandKashmir, android.R.layout.simple_spinner_item);
                }
                if (position==16){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Jharkhand, android.R.layout.simple_spinner_item);
                }
                if (position==17){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Karnataka, android.R.layout.simple_spinner_item);
                }
                if (position==18){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Kerala, android.R.layout.simple_spinner_item);
                }
                if (position==19){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Ladakh, android.R.layout.simple_spinner_item);
                }
                if (position==20){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Lakshadweep, android.R.layout.simple_spinner_item);
                }
                if (position==21){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.MadhyaPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==22){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Maharastra, android.R.layout.simple_spinner_item);
                }
                if (position==23){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Manipur, android.R.layout.simple_spinner_item);
                }
                if (position==24){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Meghalaya, android.R.layout.simple_spinner_item);
                }
                if (position==25){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Mizoram, android.R.layout.simple_spinner_item);
                }
                if (position==26){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Nagaland, android.R.layout.simple_spinner_item);
                }
                if (position==27){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Odisha, android.R.layout.simple_spinner_item);
                }
                if (position==28){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Puducherry, android.R.layout.simple_spinner_item);
                }
                if (position==29){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Punjab, android.R.layout.simple_spinner_item);
                }
                if (position==30){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Rajasthan, android.R.layout.simple_spinner_item);
                }
                if (position==31){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Sikkim, android.R.layout.simple_spinner_item);
                }
                if (position==32){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.TamilNadu, android.R.layout.simple_spinner_item);
                }
                if (position==33){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Telangana, android.R.layout.simple_spinner_item);
                }
                if (position==34){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Tripura, android.R.layout.simple_spinner_item);
                }
                if (position==35){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.UttarPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==36){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Uttarakhand, android.R.layout.simple_spinner_item);
                }
                if (position==37){
                    adapterCity = ArrayAdapter.createFromResource(getApplicationContext(), R.array.WestBengal, android.R.layout.simple_spinner_item);
                }

                adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                citySpinner.setAdapter(adapterCity);

            }

            //Till Above

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner = findViewById(R.id.citySpinnerUpdate);

        // Sign up Button
        signupBtnSignupActivity = findViewById(R.id.signupBtnSignupActivity);
        signupBtnSignupActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                validateData();

            }
        });


    }

    //Spinner State Validation
    private void setSpinnerError(Spinner spinnerState, String error){
        View selectedView = spinnerState.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinnerState.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinnerState.performClick(); // to open the spinner list if error is found.

        }
    }


    //Check if all the fields are completed.
    private void validateData() {
        name = userNameET.getText().toString();
//        dob = dateOfBirth.getText().toString();
//        gender = genderSpinner.getSelectedItem().toString();
//        bloodGroup = bloodGroupSpinner.getSelectedItem().toString();
        phoneNumber = userPhoneNumberET.getText().toString();
        state = stateSpinner.getSelectedItem().toString();
        city = citySpinner.getSelectedItem().toString();
        Email = userEmailET.getText().toString();
        Password = userPasswordET.getText().toString();

        if (name.isEmpty()){
            userNameET.setError("Please enter your Name as in Adhaar Card.");
            userNameET.requestFocus();
        }
//        else if(dob.isEmpty()){
//            dateOfBirth.setError("Please enter your Date of Birth in DD/MM/YYYY or Click on the Calendar icon to set it.");
//            dateOfBirth.requestFocus();
//        }
//        else if(gender.equals("Your Gender")){
//            setSpinnerError(genderSpinner, "Select your Gender.");
//        }
//        else if(bloodGroup.equals("Blood Group")){
//            setSpinnerError(bloodGroupSpinner, "Select your Blood Group.");
//        }
        else if(phoneNumber.isEmpty()){
            userPhoneNumberET.setError("Please enter your Phone number.");
            userPhoneNumberET.requestFocus();
        } else if(state.equals("Your State")){
            setSpinnerError(stateSpinner, "Select your State.");
        } else if(city.equals("Your City")){
            setSpinnerError(citySpinner, "Select your City.");
        } else if(Email.isEmpty()){
            userEmailET.setError("Please enter a Valid Email ID.");
            userEmailET.requestFocus();
        } else if(Password.isEmpty()){
            userPasswordET.setError("Please enter a Valid Password of your Email ID.");
            userPasswordET.requestFocus();
        } else {
            createUser();
        }

    }


    private void createUser(){
        progressBar2.setVisibility(View.VISIBLE);
        String email = userEmailET.getText().toString();
        String password = userPasswordET.getText().toString();

        mAuth = FirebaseAuth.getInstance();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uploadData();

                        } else {
                            userEmailET.setText("");
                            userPasswordET.setText("");
                            Toast.makeText(RegisterActivity.this, "Error :"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void uploadData() {
        dbReference = FirebaseDatabase.getInstance().getReference().child("Users");
        String key = dbReference.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).getKey();


        HashMap<String, String> user = new HashMap<>();
        user.put("Name", name);
//        user.put("Date_Of_Birth", dob);
//        user.put("Gender", gender);
        user.put("Phone_number", phoneNumber);
        user.put("State", state);
        user.put("City", city);
        user.put("E_mail", Email);

        dbReference.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            openSignin();
                            Toast.makeText(RegisterActivity.this, "Hi! You are now a member. Sign in Now!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressBar2.setVisibility(View.INVISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar2.setVisibility(View.INVISIBLE);
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void openSignin() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}