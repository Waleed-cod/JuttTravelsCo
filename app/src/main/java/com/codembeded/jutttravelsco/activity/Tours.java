package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForTour;
import com.codembeded.jutttravelsco.models.TourModels;

import java.util.ArrayList;

public class Tours extends AppCompatActivity {

    RecyclerView tour_rv;
    AdapterForTour adapterForTour;
    ArrayList<TourModels> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);

        tour_rv = findViewById(R.id.tour_rv);
    }
}