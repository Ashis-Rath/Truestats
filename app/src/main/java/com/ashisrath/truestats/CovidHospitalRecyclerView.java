package com.ashisrath.truestats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CovidHospitalRecyclerView extends AppCompatActivity {

    TextView showingResultsForCovRvTV, emptyRecyclerViewMessageCov, backBtnCovTV;
    String userKaCity, userKaState;
    ProgressBar progressBar5;

    RecyclerView recyclerView;
    DatabaseReference database;
    covidHospAdapter CovidAdapter;
    ArrayList<covidHospData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_hospital_recycler_view);


        // Block User Touch Events
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar5 = findViewById(R.id.progressBar5);
        progressBar5.setVisibility(View.VISIBLE);



        // Fetching Data from Home Page
        userKaCity = getIntent().getStringExtra("cityOfUser");
        userKaState = getIntent().getStringExtra("stateOfUser");

        // Initialising ImageView & TextView
        showingResultsForCovRvTV = findViewById(R.id.showingResultsForCovRvTV);
        emptyRecyclerViewMessageCov = findViewById(R.id.emptyRecyclerViewMessageCov);

        // Setting showing results
        showingResultsForCovRvTV.setText("Showing results for: " + userKaCity + ", " + userKaState);

        recyclerView = findViewById(R.id.covDbRecyclerView);
        database = FirebaseDatabase.getInstance().getReference().child("COVID Hospital");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        CovidAdapter = new covidHospAdapter(this, list);
        recyclerView.setAdapter(CovidAdapter);

        //***********************************31 May*******************************************
        // Searching Firebase for City based results
        DatabaseReference mDatabaseRef =FirebaseDatabase.getInstance().getReference("COVID Hospital");

        Query query=mDatabaseRef.orderByChild("City").equalTo(userKaCity);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data:dataSnapshot.getChildren()){

                    covidHospData covidData = data.getValue(covidHospData.class);
                    list.add(covidData);


                }

                CovidAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //***********************************31 May*******************************************

        //***********************************1 June*******************************************
        // Show Message if Recycler View is Empty
        CovidAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(CovidAdapter.getItemCount()==0){
//                            Toast.makeText(HospitalBedRecyclerView.this, "No Enteries Found.", Toast.LENGTH_SHORT).show();
                    emptyRecyclerViewMessageCov.setText("Organisations providing COVID Care facilities from " + userKaCity + ", " + userKaState + " haven't registered with us yet. Please check on us again in a few days for updates regarding this location.");
                    //You can show toast or choose any other option
                }
            }
        });
        //***********************************1 June*******************************************

        progressBar5.setVisibility(View.INVISIBLE);
        // Allow User Touch Events
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // Clicking on Back Button TV
        backBtnCovTV = findViewById(R.id.backBtnCovTV);
        backBtnCovTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}