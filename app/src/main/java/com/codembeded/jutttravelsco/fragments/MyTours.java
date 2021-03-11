package com.codembeded.jutttravelsco.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.activity.OurTours;
import com.codembeded.jutttravelsco.adapter.AdapterForTour;
import com.codembeded.jutttravelsco.models.TourModels;

import java.util.ArrayList;

public class MyTours extends Fragment {
    public static final String TITLE = "My Tours";
    private final ArrayList<TourModels> mTours_lists = new ArrayList<>();
    RecyclerView mTour_rv;
    private AdapterForTour mAdapterForTour;
    private TextView mEmpty_card_tv_tour;


    public static MyTours newInstance() {
        return new MyTours();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_tours, container, false);
        mTour_rv = v.findViewById(R.id.my_tours_frag_rv);
        mEmpty_card_tv_tour = v.findViewById(R.id.empty_tv_my_tours_frag);

        mEmpty_card_tv_tour.setVisibility(View.VISIBLE);


        return v;
    }
}