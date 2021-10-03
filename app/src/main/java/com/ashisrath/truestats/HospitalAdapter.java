package com.ashisrath.truestats;

import android.annotation.SuppressLint;
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

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {

    Context context;
    ArrayList<hospitalData> list;

    public HospitalAdapter(Context context, ArrayList<hospitalData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_hospital_layout, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        hospitalData hpData = list.get(position);
        holder.nameOfOrgTV.setText(hpData.getName());
        holder.addressOfOrgTV.setText(hpData.getAddress());
//        holder.stateOfOrgTV.setText(hpData.getState());
        holder.cityOfOrgTV.setText(hpData.getCity());

        // ICU Beds
        String TotalIcuBed = hpData.getTotal_ICU_Ventilator_Beds();
        holder.totalVentiBedTV.setText("Total: " + TotalIcuBed);

        String VacantIcuBed = hpData.getVacant_ICU_Ventilator_Beds();
        holder.vacantVentiBedTV.setText("Vacant: " + VacantIcuBed);

        // Oxygen Beds
        String TotalOxiBed = hpData.getTotal_Oxygen_Beds();
        holder.totalOxyBedTV.setText("Total: " + TotalOxiBed);

        String VacantOxiBed = hpData.getVacant_Oxygen_Beds();
        holder.vacantOxyBedTV.setText("Vacant: " + VacantOxiBed);

        // Normal Beds
        String TotalNormaBed = hpData.getTotal_Normal_Beds();
        holder.totalNormalBedTV.setText("Total: " + TotalNormaBed);

        String VacantNormaBed = hpData.getVacant_Normal_Beds();
        holder.vacantNormalBedTV.setText("Vacant: " + VacantNormaBed);

        String lastUpdateDate = hpData.getUpdate_Date();
        String lastUpdateTime = hpData.getUpdate_Time();
        holder.updateDateTimeTV.setText("Last Updated: " +lastUpdateDate + ", " + lastUpdateTime);

        String publicNumber = hpData.getPublic_Phone_number();
        holder.callBtnOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + publicNumber;
                intent.setData(Uri.parse(temp));
                context.startActivity(intent);
            }
        });

        String googleMapURL = hpData.getLocation_URL();
        holder.locationBtnOxi.setOnClickListener(new View.OnClickListener() {
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

//        TextView Address, City, Location_URL, Name, Public_Phone_number, State, Total_ICU_Ventilator_Beds,
//                Total_Normal_Beds, Total_Oxygen_Beds, Update_Date,  Update_Time, Vacant_ICU_Ventilator_Beds, Vacant_Normal_Beds, Vacant_Oxygen_Beds;

        TextView nameOfOrgTV, addressOfOrgTV, stateOfOrgTV, cityOfOrgTV, totalVentiBedTV, vacantVentiBedTV, totalOxyBedTV, vacantOxyBedTV, totalNormalBedTV,
                vacantNormalBedTV, updateDateTimeTV, callBtnOxi, locationBtnOxi;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfOrgTV = itemView.findViewById(R.id.nameOfOrgCovTV);
            addressOfOrgTV = itemView.findViewById(R.id.addressOfOrgCovTV);
//            stateOfOrgTV = itemView.findViewById(R.id.stateOfOrgOxiTV);
            cityOfOrgTV = itemView.findViewById(R.id.cityOfOrgCovTV);
            totalVentiBedTV = itemView.findViewById(R.id.totalVentiBedCovTV);
            vacantVentiBedTV = itemView.findViewById(R.id.vacantVentiBedCovTV);
            totalOxyBedTV = itemView.findViewById(R.id.totalOxyBedCovTV);
            vacantOxyBedTV = itemView.findViewById(R.id.vacantOxyBedCovTV);
            totalNormalBedTV = itemView.findViewById(R.id.totalNormalBedTV);
            vacantNormalBedTV = itemView.findViewById(R.id.vacantNormalBedTV);
            updateDateTimeTV = itemView.findViewById(R.id.updateDateTimeCovTV);
            callBtnOxi = itemView.findViewById(R.id.callBtnCov);
            locationBtnOxi = itemView.findViewById(R.id.locationBtnCov);

        }
    }
}
