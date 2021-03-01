package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.MyHistoryModels;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterForMyHistory extends RecyclerView.Adapter<AdapterForMyHistory.ViewHolder> {

    ArrayList<MyHistoryModels> data;
    Context ctx;

    public AdapterForMyHistory(ArrayList<MyHistoryModels> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_history_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForMyHistory.ViewHolder holder, int position) {

        holder.my_history_departure.setText(data.get(position).getDeparture());
        holder.my_history_arrival.setText(data.get(position).getArrival());
        holder.my_history_date.setText(data.get(position).getDate());
        holder.my_history_Time.setText(data.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView my_history_departure, my_history_arrival, my_history_date, my_history_Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.my_history_departure = itemView.findViewById(R.id.my_history_departure);
            this.my_history_arrival = itemView.findViewById(R.id.my_history_arrival);
            this.my_history_date = itemView.findViewById(R.id.my_history_date);
            this.my_history_Time = itemView.findViewById(R.id.my_history_Time);

        }
    }
}
