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

public class OxiAdapter extends RecyclerView.Adapter<OxiAdapter.mViewHolder> {

    Context oContext;
    ArrayList<oxygenDataModelClass> olist;

    public OxiAdapter(Context oContext, ArrayList<oxygenDataModelClass> olist) {
        this.oContext = oContext;
        this.olist = olist;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ov = LayoutInflater.from(oContext).inflate(R.layout.item_oxygen_layout, parent, false);
        return new mViewHolder(ov);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        oxygenDataModelClass oxiData = olist.get(position);

        holder.nameOfOrgOxiTV.setText(oxiData.getName());
        holder.addressOfOrgOxiTV.setText(oxiData.getAddress());
        holder.stateOfOrgOxiTV.setText(oxiData.getState());
        holder.cityOfOrgOxiTV.setText(oxiData.getCity());
        holder.statusUpdateOxiTV.setText(oxiData.getOxygen_Stock_Status());

        String lastUpdateDate = oxiData.getUpdate_Date();
        String lastUpdateTime = oxiData.getUpdate_Time();
        holder.updateDateTimeOxiTV.setText("Last Updated: " +lastUpdateDate + ", " + lastUpdateTime);

        String publicNumber = oxiData.getPublic_Phone_number();
        holder.callBtnOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + publicNumber;
                intent.setData(Uri.parse(temp));
                oContext.startActivity(intent);
            }
        });

        String googleMapURL = oxiData.getLocation_URL();
        holder.locationBtnOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleMapURL));
//                intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
                oContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return olist.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfOrgOxiTV, addressOfOrgOxiTV, stateOfOrgOxiTV, cityOfOrgOxiTV, statusUpdateOxiTV,
                updateDateTimeOxiTV, callBtnOxi, locationBtnOxi;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfOrgOxiTV = itemView.findViewById(R.id.nameOfOrgCovTV);
            addressOfOrgOxiTV = itemView.findViewById(R.id.addressOfOrgCovTV);
            stateOfOrgOxiTV = itemView.findViewById(R.id.stateOfOrgCovTV);
            cityOfOrgOxiTV = itemView.findViewById(R.id.cityOfOrgCovTV);
            statusUpdateOxiTV = itemView.findViewById(R.id.statusUpdateOxiTV);
            updateDateTimeOxiTV = itemView.findViewById(R.id.updateDateTimeCovTV);
            callBtnOxi = itemView.findViewById(R.id.callBtnCov);
            locationBtnOxi = itemView.findViewById(R.id.locationBtnCov);
        }
    }


}
