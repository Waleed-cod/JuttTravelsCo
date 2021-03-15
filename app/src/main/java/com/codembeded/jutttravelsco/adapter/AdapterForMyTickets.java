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

public class AdapterForMyTickets extends RecyclerView.Adapter<AdapterForMyTickets.ViewHolder> {

    ArrayList<MyBookingsModels> details;
    Context ctx;

    public AdapterForMyTickets(ArrayList<MyBookingsModels> details, Context ctx) {
        this.details = details;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tickets_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.departure.setText(details.get(position).getDeparture());
        holder.arrival.setText(details.get(position).getArrival());
        holder.date.setText(details.get(position).getDeparture_date());
        holder.time.setText(details.get(position).getTime());
        holder.numTickets.setText(details.get(position).getRate_parcel_vehicle());
        holder.ac_status_Tickets.setText(details.get(position).getAc_status());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView arrival, departure, date, time, numTickets, ac_status_Tickets;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            departure = itemView.findViewById(R.id.departure_date_my_tickets_frag_box);
            arrival = itemView.findViewById(R.id.arrival_my_tickets_frag_box);
            date = itemView.findViewById(R.id.departure_date_my_tickets_frag_box);
            time = itemView.findViewById(R.id.time_my_tickets_frag_box);
            numTickets = itemView.findViewById(R.id.ticket_rate_my_tickets_frag_box);
            ac_status_Tickets = itemView.findViewById(R.id.ac_status_my_tickets_frag_box);

        }
    }
}
