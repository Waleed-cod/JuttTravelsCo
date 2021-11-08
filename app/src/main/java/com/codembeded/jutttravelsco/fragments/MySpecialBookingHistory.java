package com.codembeded.jutttravelsco.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.codembeded.jutttravelsco.adapter.AdapterForMyBookings;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.MyBookingsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MySpecialBookingHistory extends Fragment {


    public static final String TITLE = "Special Booking";

    private RecyclerView mMySpecialBookings_rv;
    private AdapterForMyBookings mAdapterForMySpecialBookings;
    private final ArrayList<MyBookingsModels> mMySpecialBookings_list = new ArrayList<>();
    private TextView empty_card_tv_mMySpecialBookings;
    SharedPreferences preferences;
    private String user_id;
    ProgressBar progressBar;

    public static Fragment newInstance() {
        return new MySpecialBookingHistory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_special_booking_history, container, false);
        progressBar = v.findViewById(R.id.progress_my_special_booking_history_frag);

        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");

        mMySpecialBookings_rv = v.findViewById(R.id.my_special_booking_history_frag_rv);
        empty_card_tv_mMySpecialBookings = v.findViewById(R.id.empty_tv_my_special_booking_history_frag);
        getSpecialBooking(user_id);

        return v;
    }

    private void getSpecialBooking(final String passenger_id) {
        String tag_str_req = "req_get_special_booking";
        progressBar.setVisibility(View.VISIBLE);
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_SPECIAL_BOOKING_HISTORY + "?passenger_id=" + passenger_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("special_bookings");

                        if (jsonArray.length() == 0) {
                            empty_card_tv_mMySpecialBookings.setVisibility(View.VISIBLE);
                            mMySpecialBookings_rv.setVisibility(View.GONE);
                        } else {

                            empty_card_tv_mMySpecialBookings.setVisibility(View.GONE);
                            mMySpecialBookings_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                mMySpecialBookings_list.add(new MyBookingsModels(
                                        jsonObject.getInt("special_booking_id"),
                                        jsonObject.getInt("vehicle_id"),
                                        jsonObject.getInt("amount"),
                                        jsonObject.getInt("discounted_amount"),
                                        jsonObject.getInt("special_booking_status"),
                                        jsonObject.getInt("extra_mileage"),
                                        jsonObject.getInt("vehicle_ac_status"),
                                        jsonObject.getString("special_booking_date"),
                                        jsonObject.getString("booking_date"),
                                        jsonObject.getString("booking_time"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("departure"),
                                        jsonObject.getString("arrival"),
                                        jsonObject.getString("vehicle_name"),
                                        jsonObject.getString("vehicle_number")));
                            }
                        }
                        mAdapterForMySpecialBookings = new AdapterForMyBookings(mMySpecialBookings_list, getActivity());
                        mMySpecialBookings_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        mMySpecialBookings_rv.setAdapter(mAdapterForMySpecialBookings);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error: ", error.getMessage());
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