package com.codembeded.jutttravelsco.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.activity.MyBookings;
import com.codembeded.jutttravelsco.adapter.AdapterForMyTickets;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import java.util.ArrayList;

public class MyTickets extends Fragment {

    public static final String TITLE = "My Tickets";
    RecyclerView mMyTicket_rv;
    AdapterForMyTickets mAdapterForMyTickets;
    ArrayList<MyBookingsModels> bookings_list = new ArrayList<>();
    TextView mEmpty_card_tv_myTickets;

    public static MyTickets newInstance() {

        return new MyTickets();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_tickets, container, false);
        mMyTicket_rv = v.findViewById(R.id.my_tickets_frag_rv);
        mEmpty_card_tv_myTickets = v.findViewById(R.id.empty_tv_my_tickets_frag);

        mEmpty_card_tv_myTickets.setVisibility(View.VISIBLE);
        return v;
    }
}