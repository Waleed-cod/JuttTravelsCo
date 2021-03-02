package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForMyBookings;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import java.util.ArrayList;

public class MyBookings extends AppCompatActivity {

    RecyclerView myBooking_rv;
    AdapterForMyBookings adapterForMyBookings;
    ArrayList<MyBookingsModels> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        myBooking_rv = findViewById(R.id.my_bookings_rv);
    }
}