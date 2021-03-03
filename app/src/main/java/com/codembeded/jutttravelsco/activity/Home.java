package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForHomeSlider;
import com.codembeded.jutttravelsco.models.HomeImageSliderModels;
import com.google.android.material.card.MaterialCardView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private SliderView imageSlider;
    private final ArrayList<HomeImageSliderModels> items = new ArrayList<>();
    private AdapterForHomeSlider imageSliderAdapter;

    MaterialCardView bookMyTickets_cv, bookMyRide_cv, myTicket_cv, tour_cv, history_cv, complain_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSlider = findViewById(R.id.home_image_slider);
        bookMyTickets_cv = findViewById(R.id.book_my_tickets_home);
        bookMyRide_cv = findViewById(R.id.book_my_ride_home);
        myTicket_cv = findViewById(R.id.my_tickets_home);
        tour_cv = findViewById(R.id.tour_home);
        history_cv = findViewById(R.id.history_home);
        complain_cv = findViewById(R.id.complain_home);

        setImageSlider();
        OnClicks();

    }

    private void OnClicks() {
        bookMyTickets_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, BookMyTicket.class);
                startActivity(intent);
            }
        });

        bookMyRide_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, SpecialBooking.class);
                startActivity(i);
            }
        });
        myTicket_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, BookMyTicket.class);
                startActivity(i);
            }
        });
        history_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MyHistory.class);
                startActivity(i);
            }
        });
        tour_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, OurTours.class);
                startActivity(i);
            }
        });
        complain_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Complaints.class);
                startActivity(i);
            }
        });
    }

    private void setImageSlider() {

        items.add(new HomeImageSliderModels(R.drawable.world_tour));
        items.add(new HomeImageSliderModels(R.drawable.world_tour_beauty));
        imageSliderAdapter = new AdapterForHomeSlider(items, Home.this);
        imageSlider.setSliderAdapter(imageSliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(3);
        imageSlider.setAutoCycle(true);
        imageSlider.startAutoCycle();
    }
}