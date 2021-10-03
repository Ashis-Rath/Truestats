package com.ashisrath.truestats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HospitalBedRecyclerView extends AppCompatActivity {
    ImageView backbtnManual;
    String userKaCity, userKaState;
    TextView showingResultsForTV, emptyRecyclerViewMessage, backBtnHospBedTV;
    ProgressBar progressBar3;

    RecyclerView recyclerView;
    DatabaseReference database;
    HospitalAdapter hospitalAdapter;
    ArrayList<hospitalData> list;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_bed_recycler_view);

        progressBar3 = findViewById(R.id.progressBar3);
        // Block User Touch Events
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar3.setVisibility(View.VISIBLE);


        // Fetching Data from Home Page
        userKaCity = getIntent().getStringExtra("cityOfUser");
        userKaState = getIntent().getStringExtra("stateOfUser");

        // Initialising ImageView & TextView
        showingResultsForTV = findViewById(R.id.showingResultsForCovRvTV);
        emptyRecyclerViewMessage = findViewById(R.id.emptyRecyclerViewMessageCov);


        // Setting showing results
        showingResultsForTV.setText("Showing results for: " + userKaCity + ", " + userKaState);

        recyclerView = findViewById(R.id.covDbRecyclerView);
        database = FirebaseDatabase.getInstance().getReference().child("Hospital");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        hospitalAdapter = new HospitalAdapter(this, list);
        recyclerView.setAdapter(hospitalAdapter);



        //***********************************31 May*******************************************
        // Searching Firebase for City based results
        DatabaseReference mDatabaseRef =FirebaseDatabase.getInstance().getReference("Hospital");

        Query query=mDatabaseRef.orderByChild("City").equalTo(userKaCity);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data:dataSnapshot.getChildren()){


                    hospitalData hpData=data.getValue(hospitalData.class);
                    list.add(hpData);

                }

                hospitalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //***********************************31 May*******************************************

        //***********************************1 June*******************************************
        // Show Message if Recycler View is Empty
        hospitalAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        if(hospitalAdapter.getItemCount()==0){
//                            Toast.makeText(HospitalBedRecyclerView.this, "No Enteries Found.", Toast.LENGTH_SHORT).show();
                            emptyRecyclerViewMessage.setText("Organisations providing health care facilities from " + userKaCity + ", " + userKaState + " haven't registered with us yet. Please check on us again in a few days for updates regarding this location.");
                            //You can show toast or choose any other option
                        }
                    }
                });
        //***********************************1 June*******************************************

        progressBar3.setVisibility(View.INVISIBLE);
        // Allow User Touch Events
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

//        // Clicking on Back Button
//        backbtnManual = findViewById(R.id.backbtnUserSettings);
//        backbtnManual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        // Clicking on Back Button TV
        backBtnHospBedTV = findViewById(R.id.backBtnCovTV);
        backBtnHospBedTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //*********************Showing Data of a Particular City*************************
    private void processSearch(){

    }

    //*********************Showing Data of a Particular City*************************
}