package com.codembeded.jutttravelsco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForHomeSlider;
import com.codembeded.jutttravelsco.models.HomeImageSliderModels;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Home extends AppCompatActivity {

    private SliderView imageSlider;
    private final ArrayList<HomeImageSliderModels> items = new ArrayList<>();
    private AdapterForHomeSlider imageSliderAdapter;
    private static final String TAG = Home.class.getSimpleName();
    MaterialCardView bookMyTickets_cv, specialBooking_cv, myBookings_cv, tour_cv, history_cv, complain_cv, new_parcel_cv;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        init();
        setImageSlider();
        OnClicks();
//        get_slider_images();
    }


    private void init() {
        bottomSheetDialog = new BottomSheetDialog(Home.this);
        imageSlider = findViewById(R.id.home_image_slider);
        bookMyTickets_cv = findViewById(R.id.book_my_tickets_home);
        specialBooking_cv = findViewById(R.id.special_booking_home);
        myBookings_cv = findViewById(R.id.my_bookings_home);
        tour_cv = findViewById(R.id.tour_home);
        history_cv = findViewById(R.id.history_home);
        complain_cv = findViewById(R.id.complain_home);
        new_parcel_cv = findViewById(R.id.new_parcel_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                showBottomSheet();
                break;
            case R.id.log_out:
                sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("id").apply();
                startActivity(new Intent(Home.this, Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBottomSheet() {


        bottomSheetDialog.setContentView(R.layout.bottom_navigation_box);

        bottomSheetDialog.show();
    }

    //    private void get_slider_images() {
//
//        // Tag use to cancel the request
//        String tag_str_req = "req_get_images";
//
//        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_SLIDER_IMAGE, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "!st Response:" + response);
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    Log.e(" second response:", response);
//                    boolean error = jObj.getBoolean("error");
//                    //check for error node in json
//                    if (!error) {
//                        JSONArray jArray = jObj.getJSONArray("images");
//                        for (int i = 0; i < jArray.length(); i++) {
//                            items.add(new HomeImageSliderModels(
//                                    jArray.getJSONObject(i).getString("images")
//
//                            ));
//
//                        }
//                        imageSliderAdapter = new AdapterForHomeSlider(items, Home.this);
//                        imageSlider.setSliderAdapter(imageSliderAdapter);
//
//
//                    } else {
//                        String error_msg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Volley Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                return params;
//            }
//        };
//
//        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
//    }

    private void OnClicks() {
        bookMyTickets_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, BookMyTicket.class);
                startActivity(intent);
            }
        });

        specialBooking_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, SpecialBooking.class);
                startActivity(i);
            }
        });
        myBookings_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MyBookings.class);
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

        new_parcel_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, NewParcel.class);
                startActivity(i);
            }
        });
    }

    private void setImageSlider() {

        items.add(new HomeImageSliderModels(R.drawable.courier_deliveries));
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