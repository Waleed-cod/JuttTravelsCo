package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.GetTicketsModels;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForMyTickets extends RecyclerView.Adapter<AdapterForMyTickets.ViewHolder> {

    ArrayList<GetTicketsModels> details;
    Context ctx;

    public AdapterForMyTickets(ArrayList<GetTicketsModels> details, Context ctx) {
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

        holder.my_routes.setText(details.get(position).getRoute_name());
        holder.booking_date.setText(details.get(position).getBooking_date());
        holder.departure_time.setText(details.get(position).getDeparture_time());
        holder.arrival_time.setText(details.get(position).getArrival_time());
        holder.total_seats.setText(String.valueOf(details.get(position).getTotal_seats()));
        holder.women_seats.setText(String.valueOf(details.get(position).getLadies_seats()));
        holder.ac_status_Tickets.setText(String.valueOf(details.get(position).getAc_status()));
        holder.total_amount.setText(String.valueOf(details.get(position).getTotal_amount()));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView my_routes, booking_date, departure_time,arrival_time, total_seats, women_seats, ac_status_Tickets, total_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            my_routes = itemView.findViewById(R.id.route_my_tickets_frag_box);
            booking_date = itemView.findViewById(R.id.booking_date_my_tickets_frag_box);
            departure_time = itemView.findViewById(R.id.departure_time_my_tickets_frag_box);
            arrival_time = itemView.findViewById(R.id.arrival_time_my_tickets_frag_box);
            total_seats = itemView.findViewById(R.id.total_seats_my_tickets_frag_box);
            women_seats = itemView.findViewById(R.id.women_seats_my_tickets_frag_box);
            ac_status_Tickets = itemView.findViewById(R.id.ac_status_my_tickets_frag_box);
            total_amount = itemView.findViewById(R.id.total_amount_my_tickets_frag_box);

        }
    }
}
