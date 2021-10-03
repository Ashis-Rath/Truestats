package com.ashisrath.truestats;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editLocation extends AppCompatActivity {
    Spinner stateSpinnerUpdate, citySpinnerUpdate;
    ArrayAdapter<CharSequence> adapterCityTDM;
    Button updateBtnEditLocation, cancelBtnEditLocation;
    String newStateOfUser, newCityOfUser, userUIDm;
    DatabaseReference userDb;
    ProgressBar progressBar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);

        // Fetching Data from Settings
        userUIDm = getIntent().getStringExtra("UserKaIDAgain");

        // Database Stuff
        userDb = FirebaseDatabase.getInstance().getReference().child("Users");

        progressBar10 = findViewById(R.id.progressBar10);




        // Spinner Stuff
        //State Spinner
        stateSpinnerUpdate = findViewById(R.id.stateSpinnerUpdate);
        ArrayAdapter<CharSequence> adapterStateTDM = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapterStateTDM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinnerUpdate.setAdapter(adapterStateTDM);
        stateSpinnerUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.YourCity, android.R.layout.simple_spinner_item);
                }
                if (position==1){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.AndamanandNicobar, android.R.layout.simple_spinner_item);
                }
                if (position==2){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.AndhraPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==3){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ArunachalPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==4){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Assam, android.R.layout.simple_spinner_item);
                }
                if (position==5){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Bihar, android.R.layout.simple_spinner_item);
                }
                if (position==6){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Chandigarh, android.R.layout.simple_spinner_item);
                }
                if (position==7){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Chattisgarh, android.R.layout.simple_spinner_item);
                }
                if (position==8){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.DadraandNagarHaveli, android.R.layout.simple_spinner_item);
                }
                if (position==9){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Damananddiu, android.R.layout.simple_spinner_item);
                }
                if (position==10){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Delhi, android.R.layout.simple_spinner_item);
                }
                if (position==11){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Goa, android.R.layout.simple_spinner_item);
                }
                if (position==12){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Gujurat, android.R.layout.simple_spinner_item);
                }
                if (position==13){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Haryana, android.R.layout.simple_spinner_item);
                }
                if (position==14){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.HimachalPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==15){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.JammuandKashmir, android.R.layout.simple_spinner_item);
                }
                if (position==16){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Jharkhand, android.R.layout.simple_spinner_item);
                }
                if (position==17){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Karnataka, android.R.layout.simple_spinner_item);
                }
                if (position==18){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Kerala, android.R.layout.simple_spinner_item);
                }
                if (position==19){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Ladakh, android.R.layout.simple_spinner_item);
                }
                if (position==20){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Lakshadweep, android.R.layout.simple_spinner_item);
                }
                if (position==21){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.MadhyaPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==22){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Maharastra, android.R.layout.simple_spinner_item);
                }
                if (position==23){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Manipur, android.R.layout.simple_spinner_item);
                }
                if (position==24){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Meghalaya, android.R.layout.simple_spinner_item);
                }
                if (position==25){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Mizoram, android.R.layout.simple_spinner_item);
                }
                if (position==26){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Nagaland, android.R.layout.simple_spinner_item);
                }
                if (position==27){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Odisha, android.R.layout.simple_spinner_item);
                }
                if (position==28){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Puducherry, android.R.layout.simple_spinner_item);
                }
                if (position==29){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Punjab, android.R.layout.simple_spinner_item);
                }
                if (position==30){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Rajasthan, android.R.layout.simple_spinner_item);
                }
                if (position==31){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Sikkim, android.R.layout.simple_spinner_item);
                }
                if (position==32){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.TamilNadu, android.R.layout.simple_spinner_item);
                }
                if (position==33){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Telangana, android.R.layout.simple_spinner_item);
                }
                if (position==34){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Tripura, android.R.layout.simple_spinner_item);
                }
                if (position==35){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.UttarPradesh, android.R.layout.simple_spinner_item);
                }
                if (position==36){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Uttarakhand, android.R.layout.simple_spinner_item);
                }
                if (position==37){
                    adapterCityTDM = ArrayAdapter.createFromResource(getApplicationContext(), R.array.WestBengal, android.R.layout.simple_spinner_item);
                }

                adapterCityTDM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                citySpinnerUpdate.setAdapter(adapterCityTDM);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinnerUpdate = findViewById(R.id.citySpinnerUpdate);

        // Cancel Button
        cancelBtnEditLocation = findViewById(R.id.cancelBtnEditLocation);
        cancelBtnEditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Update Button
        updateBtnEditLocation = findViewById(R.id.updateBtnEditLocation);
        updateBtnEditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateDataEditLocationURL();
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

    private void ValidateDataEditLocationURL() {
        newStateOfUser = stateSpinnerUpdate.getSelectedItem().toString();
        newCityOfUser = citySpinnerUpdate.getSelectedItem().toString();

        if(newStateOfUser.equals("Your State")){
            setSpinnerError(stateSpinnerUpdate, "Fill State");
        } else if(newCityOfUser.equals("Your City")){
            setSpinnerError(citySpinnerUpdate, "Fill City");
        } else {
            updateLocation();
        }
    }

    private void updateLocation() {
        progressBar10.setVisibility(View.VISIBLE);
        userDb.child(userUIDm).child("City").setValue(newCityOfUser);
        userDb.child(userUIDm).child("State").setValue(newStateOfUser);
        finish();
    }
}