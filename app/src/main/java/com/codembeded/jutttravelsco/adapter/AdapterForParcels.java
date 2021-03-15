package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.MyBookingsModels;
import com.codembeded.jutttravelsco.models.ParcelModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForParcels extends RecyclerView.Adapter<AdapterForParcels.ViewHolder> {

    ArrayList<ParcelModels> details;
    Context ctx;

    public AdapterForParcels(ArrayList<ParcelModels> details, Context ctx) {
        this.details = details;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AdapterForParcels.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_parcel_frag_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForParcels.ViewHolder holder, int position) {
        holder.receiver_name.setText(details.get(position).getReceiver_name());
        holder.receiver_contact.setText(details.get(position).getReceiver_contact());
        holder.parcel_weight.setText(details.get(position).getParcel_weight());
        holder.parcel_quantity.setText(details.get(position).getParcel_quantity());
        holder.parcel_description.setText(details.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView receiver_name, receiver_contact, parcel_weight, parcel_quantity, parcel_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            receiver_name = itemView.findViewById(R.id.receiver_name_my_parcels_frag_box);
            receiver_contact = itemView.findViewById(R.id.receiver_contact_my_parcels_frag_box);
            parcel_weight = itemView.findViewById(R.id.weight_my_parcels_frag_box);
            parcel_quantity = itemView.findViewById(R.id.quantity_my_parcels_frag_box);
            parcel_description = itemView.findViewById(R.id.desc_my_parcel_frag_box);
        }
    }
}
