package com.codembeded.jutttravelsco.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForMyBookings;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import java.util.ArrayList;

public class MySpecialBookings extends Fragment {

    public static final String TITLE = "Special Bookings";
    RecyclerView mMySpecialBookings_rv;
    AdapterForMyBookings mAdapterForMySpecialBookings;
    ArrayList<MyBookingsModels> mMySpecialBookings_list = new ArrayList<>();
    TextView empty_card_tv_mMySpecialBookings;

    public static MySpecialBookings newInstance() {
        return new MySpecialBookings();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_special_bookings, container, false);

        mMySpecialBookings_rv = v.findViewById(R.id.my_special_bookings_frag_rv);
        empty_card_tv_mMySpecialBookings = v.findViewById(R.id.empty_tv_my_special_bookings_frag);

        empty_card_tv_mMySpecialBookings.setVisibility(View.VISIBLE);

        return v;
    }
}