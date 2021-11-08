package com.codembeded.jutttravelsco.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterGetTour;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.TourModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTours extends Fragment {
    public static final String TITLE = "My Tours";
    private final ArrayList<TourModels> mGetTours_lists = new ArrayList<>();
    RecyclerView mTour_rv;
    private AdapterGetTour mAdapterGetTour;
    private TextView mEmpty_card_tv_tour;
    String user_id;
    SharedPreferences preferences;
    ProgressBar progressBar;


    public static MyTours newInstance() {
        return new MyTours();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_tours, container, false);
        mTour_rv = v.findViewById(R.id.get_my_tours_frag_rv);
        mEmpty_card_tv_tour = v.findViewById(R.id.empty_tv_my_tours_frag);
        progressBar = v.findViewById(R.id.progress_my_tours_frag);

        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");

        getTourBooking(user_id);
        return v;
    }

    private void getTourBooking(final String passenger_id) {

        String tag_str_req = "req_get_tour_bookings";
        progressBar.setVisibility(View.VISIBLE);


        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.GET_TOUR_BOOKING + "?passenger_id=" + passenger_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("tours");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (jsonArray.length() == 0) {

                                mEmpty_card_tv_tour.setVisibility(View.VISIBLE);
                                mTour_rv.setVisibility(View.GONE);

                            } else {
                                mEmpty_card_tv_tour.setVisibility(View.GONE);
                                mTour_rv.setVisibility(View.VISIBLE);

                                mGetTours_lists.add(new TourModels(jsonObject.getString("image"),
                                        jsonObject.getString("name"),
                                        jsonObject.getString("start_date"),
                                        jsonObject.getString("end_date"),
                                        jsonObject.getString("departure_time"),
                                        jsonObject.getString("description"),
                                        jsonObject.getInt("tour_id"),
                                        jsonObject.getInt("tour_status"),
                                        jsonObject.getInt("tour_booking_id"),
                                        jsonObject.getInt("amount"),
                                        jsonObject.getInt("discounted_amount"),
                                        jsonObject.getInt("total_seats"),
                                        jsonObject.getInt("rate_per_seat"),
                                        jsonObject.getString("tour_entry_date")));

                            }
                        }
                        mAdapterGetTour = new AdapterGetTour(mGetTours_lists, getActivity());
                        mTour_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        mTour_rv.setAdapter(mAdapterGetTour);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(), error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }
}
