package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.TourModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForTour extends RecyclerView.Adapter<AdapterForTour.ViewHolder> {

    ArrayList<TourModels> data;
    Context ctx;

    public AdapterForTour(ArrayList<TourModels> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_history_tour_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tour_departure.setText(data.get(position).getDeparture());
        holder.tour_arrival.setText(data.get(position).getArrival());
        holder.tour_date.setText(data.get(position).getDate());
        holder.tour_Time.setText(data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tour_departure, tour_arrival, tour_date, tour_Time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tour_departure = itemView.findViewById(R.id.booking_history_tour_departure);
            this.tour_arrival = itemView.findViewById(R.id.booking_history_tour_arrival);
            this.tour_date = itemView.findViewById(R.id.booking_history_tour_date);
            this.tour_Time = itemView.findViewById(R.id.booking_history_tour_Time);

        }
    }
}
