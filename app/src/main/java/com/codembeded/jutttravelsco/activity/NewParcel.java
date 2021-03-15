package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.codembeded.jutttravelsco.R;

public class NewParcel extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_parcel);

        toolbar = findViewById(R.id.parcel_toolbar);
        setSupportActionBar(toolbar);
    }
}