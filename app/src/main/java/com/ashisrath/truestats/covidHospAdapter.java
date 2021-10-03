package com.ashisrath.truestats;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class covidHospAdapter extends RecyclerView.Adapter<covidHospAdapter.MyViewHolder> {

    Context context;
    ArrayList<covidHospData> list;

    public covidHospAdapter(Context context, ArrayList<covidHospData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_covid_hospital_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        covidHospData chData = list.get(position);
        holder.nameOfOrgCovTV.setText(chData.getName());
        holder.addressOfOrgCovTV.setText(chData.getAddress());
        holder.cityOfOrgCovTV.setText(chData.getCity());
        holder.stateOfOrgCovTV.setText(chData.getState());

        // ICU Beds
        String TotalIcuBed = chData.getTotal_ICU_Ventilator_Beds();
        holder.totalVentiBedCovTV.setText("Total: " + TotalIcuBed);

        String VacantIcuBed = chData.getVacant_ICU_Ventilator_Beds();
        holder.vacantVentiBedCovTV.setText("Vacant: " + VacantIcuBed);

        // Oxygen Beds
        String TotalOxiBed = chData.getTotal_Oxygen_Beds();
        holder.totalOxyBedCovTV.setText("Total: " + TotalOxiBed);

        String VacantOxiBed = chData.getVacant_Oxygen_Beds();
        holder.vacantOxyBedCovTV.setText("Vacant: " + VacantOxiBed);

        String lastUpdateDate = chData.getUpdate_Date();
        String lastUpdateTime = chData.getUpdate_Time();
        holder.updateDateTimeCovTV.setText("Last Updated: " +lastUpdateDate + ", " + lastUpdateTime);

        String publicNumber = chData.getPublic_Phone_number();
        holder.callBtnCov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + publicNumber;
                intent.setData(Uri.parse(temp));
                context.startActivity(intent);
            }
        });

        String googleMapURL = chData.getLocation_URL();
        holder.locationBtnCov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleMapURL));
//                intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfOrgCovTV, addressOfOrgCovTV, cityOfOrgCovTV, stateOfOrgCovTV, totalVentiBedCovTV, vacantVentiBedCovTV, totalOxyBedCovTV
                , vacantOxyBedCovTV, callBtnCov, locationBtnCov, updateDateTimeCovTV;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfOrgCovTV = itemView.findViewById(R.id.nameOfOrgCovTV);
            addressOfOrgCovTV = itemView.findViewById(R.id.addressOfOrgCovTV);
            cityOfOrgCovTV = itemView.findViewById(R.id.cityOfOrgCovTV);
            stateOfOrgCovTV = itemView.findViewById(R.id.stateOfOrgCovTV);
            totalVentiBedCovTV = itemView.findViewById(R.id.totalVentiBedCovTV);
            vacantVentiBedCovTV = itemView.findViewById(R.id.vacantVentiBedCovTV);
            totalOxyBedCovTV = itemView.findViewById(R.id.totalOxyBedCovTV);
            vacantOxyBedCovTV = itemView.findViewById(R.id.vacantOxyBedCovTV);
            callBtnCov = itemView.findViewById(R.id.callBtnCov);
            locationBtnCov = itemView.findViewById(R.id.locationBtnCov);
            updateDateTimeCovTV = itemView.findViewById(R.id.updateDateTimeCovTV);
        }
    }

}
