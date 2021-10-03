package com.ashisrath.truestats;

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

public class OxygenDbToRecyclerView extends AppCompatActivity {
    TextView backBtnTVO, showingResultsForOxiRvTV, emptyRecyclerViewMessage;
    ProgressBar progressBar4;
    RecyclerView oxiDbRecyclerView;
    DatabaseReference database;
    OxiAdapter oxygenAdapter;
    String userKaCity, userKaState;
    ArrayList<oxygenDataModelClass> oxygenlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen_db_to_recycler_view);

        progressBar4 = findViewById(R.id.progressBar4);
        // Block User Touch Events
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar4.setVisibility(View.VISIBLE);


        // Fetching Data from Home Page
        userKaCity = getIntent().getStringExtra("cityOfUser");
        userKaState = getIntent().getStringExtra("stateOfUser");
        emptyRecyclerViewMessage = findViewById(R.id.emptyRecyclerViewMessageCov);
        showingResultsForOxiRvTV = findViewById(R.id.showingResultsForCovRvTV);
        showingResultsForOxiRvTV.setText("Showing results for: " + userKaCity + ", " + userKaState);

        // Clicking on Back Button
        backBtnTVO = findViewById(R.id.backBtnTVO);
        backBtnTVO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        oxiDbRecyclerView = findViewById(R.id.covDbRecyclerView);
        database = FirebaseDatabase.getInstance().getReference().child("Oxygen Supplier");
        oxiDbRecyclerView.setHasFixedSize(true);
        oxiDbRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        oxygenlist = new ArrayList<>();
        oxygenAdapter = new OxiAdapter(this, oxygenlist);
        oxiDbRecyclerView.setAdapter(oxygenAdapter);

        //***********************************2 June*******************************************
        // Searching Firebase for City based results
        DatabaseReference mDatabaseRef =FirebaseDatabase.getInstance().getReference("Oxygen Supplier");

        Query query=mDatabaseRef.orderByChild("City").equalTo(userKaCity);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data:dataSnapshot.getChildren()){


                    oxygenDataModelClass oxygenData = data.getValue(oxygenDataModelClass.class);
                    oxygenlist.add(oxygenData);

                }

                oxygenAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //***********************************2 June*******************************************

        //***********************************1 June*******************************************
        // Show Message if Recycler View is Empty
        oxygenAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(oxygenAdapter.getItemCount()==0){
                    emptyRecyclerViewMessage.setText("Organisations providing Oxygen facilities from " + userKaCity + ", " + userKaState + " haven't registered with us yet. Please check on us again in a few days for updates regarding this location.");
                    //You can show toast or choose any other option
                }
            }
        });
        //***********************************1 June*******************************************

        progressBar4.setVisibility(View.INVISIBLE);
        // Allow User Touch Events
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


//        oxiDbRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<oxygenDataModelClass> options =
//                new FirebaseRecyclerOptions.Builder<oxygenDataModelClass>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oxygen Supplier"), oxygenDataModelClass.class)
//                        .build();
//
//        adapter = new OxiAdapter(options);
//        oxiDbRecyclerView.setAdapter(adapter);


    }



}