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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tour_departure.setText(data.get(position).getDeparture());
        holder.tour_arrival.setText(data.get(position).getArrival());
        holder.tour_departure_date.setText(data.get(position).getDeparture_date());
        holder.tour_arrival_date.setText(data.get(position).getArrival_date());
        holder.tour_time.setText(data.get(position).getTime());
        holder.tour_person_rate.setText(data.get(position).getRate_per_person());
        holder.tour_desc.setText(data.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tour_departure, tour_arrival, tour_departure_date, tour_arrival_date, tour_time, tour_person_rate, tour_desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tour_departure = itemView.findViewById(R.id.departure_tour_box);
            this.tour_arrival = itemView.findViewById(R.id.arrival_tour_box);
            this.tour_departure_date = itemView.findViewById(R.id.departure_date_tour_box);
            this.tour_arrival_date = itemView.findViewById(R.id.arrival_date_tour_box);
            this.tour_time = itemView.findViewById(R.id.time_tour_box);
            this.tour_person_rate = itemView.findViewById(R.id.person_rate_tour_box);
            this.tour_desc = itemView.findViewById(R.id.desc_tour_box);

        }
    }
}
