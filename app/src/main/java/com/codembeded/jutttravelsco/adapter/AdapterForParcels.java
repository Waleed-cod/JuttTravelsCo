package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForParcels extends RecyclerView.Adapter<AdapterForParcels.ViewHolder> {

    ArrayList<MyBookingsModels> details;
    Context ctx;

    @NonNull
    @Override
    public AdapterForParcels.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_parcel_frag_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForParcels.ViewHolder holder, int position) {
        holder.departure.setText(details.get(position).getDeparture());
        holder.arrival.setText(details.get(position).getArrival());
        holder.date.setText(details.get(position).getDeparture_date());
        holder.time.setText(details.get(position).getTime());
        holder.parcel.setText(details.get(position).getRate_parcel_vehicle());
        holder.ac_status.setText(details.get(position).getAc_status());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView arrival, departure, date, time, parcel, ac_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            departure = itemView.findViewById(R.id.departure_date_my_parcels_frag_box);
            arrival = itemView.findViewById(R.id.arrival_my_parcels_frag_box);
            date = itemView.findViewById(R.id.departure_date_my_parcels_frag_box);
            time = itemView.findViewById(R.id.time_my_parcels_frag_box);
            parcel = itemView.findViewById(R.id.my_parcels_frag_parcel_rate_box);
            ac_status = itemView.findViewById(R.id.ac_status_my_parcel_frag_box);
        }
    }
}
