package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForHomeSlider;
import com.codembeded.jutttravelsco.adapter.AdapterForMyHistory;
import com.codembeded.jutttravelsco.models.MyHistoryModels;

import java.util.ArrayList;

public class MyHistory extends AppCompatActivity {


    RecyclerView my_history_rv;
    private AdapterForMyHistory adapterForMyHistory;
    private ArrayList<MyHistoryModels> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        my_history_rv = findViewById(R.id.my_history_rv);

    }
}