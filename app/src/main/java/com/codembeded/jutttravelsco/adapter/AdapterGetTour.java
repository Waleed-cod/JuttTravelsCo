package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.TourModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterGetTour extends RecyclerView.Adapter<AdapterGetTour.ViewHolder> {

    ArrayList<TourModels> list;
    Context ctx;

    public AdapterGetTour(ArrayList<TourModels> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_my_tour_booking_box, parent,false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.tour_image.setText(data.get(position).getRoutes());
        holder.routes.setText(list.get(position).getRoutes());
        holder.tour_departure_date.setText(list.get(position).getDeparture_date());
        holder.tour_arrival_date.setText(list.get(position).getArrival_date());
        holder.tour_time.setText(list.get(position).getTime());
        holder.total_amount.setText("Rs:  " + list.get(position).getTotal_amount());
        holder.tour_desc.setText(list.get(position).getDesc());
        holder.total_seats.setText(String.valueOf(list.get(position).getTotal_seats()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView  routes, tour_departure_date, tour_arrival_date, tour_time,
                total_amount, tour_desc, total_seats;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.routes = itemView.findViewById(R.id.routes_get_tour_booking_box);
            this.tour_departure_date = itemView.findViewById(R.id.departure_date_get_tour_booking_box);
            this.tour_arrival_date = itemView.findViewById(R.id.arrival_date_get_tour_booking_box);
            this.tour_time = itemView.findViewById(R.id.time_get_tour_booking_box);
            this.total_amount = itemView.findViewById(R.id.total_amount_get_tour_booking_box);
            this.tour_desc = itemView.findViewById(R.id.desc_get_tour_booking_box);
            this.total_seats = itemView.findViewById(R.id.total_seats_get_tour_booking_box);
        }
    }
}
