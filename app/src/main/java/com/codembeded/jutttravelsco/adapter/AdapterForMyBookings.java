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

public class AdapterForMyBookings extends RecyclerView.Adapter<AdapterForMyBookings.ViewHolder> {

    ArrayList<MyBookingsModels> data;
    Context ctx;

    public AdapterForMyBookings(ArrayList<MyBookingsModels> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AdapterForMyBookings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_history_tour_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForMyBookings.ViewHolder holder, int position) {
        holder.my_bookings_departure.setText(data.get(position).getDeparture());
        holder.my_bookings_arrival.setText(data.get(position).getArrival());
        holder.my_bookings_date.setText(data.get(position).getDate());
        holder.my_bookings_Time.setText(data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView my_bookings_departure, my_bookings_arrival, my_bookings_date, my_bookings_Time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.my_bookings_departure = itemView.findViewById(R.id.booking_history_tour_departure);
            this.my_bookings_arrival = itemView.findViewById(R.id.booking_history_tour_arrival);
            this.my_bookings_date = itemView.findViewById(R.id.booking_history_tour_date);
            this.my_bookings_Time = itemView.findViewById(R.id.booking_history_tour_Time);

        }
    }
}
