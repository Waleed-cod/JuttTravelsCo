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
import com.codembeded.jutttravelsco.adapter.AdapterForMyTickets;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.GetTicketsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTickets extends Fragment {

    public static final String TITLE = "My Tickets";
    RecyclerView mMyTicket_rv;
    AdapterForMyTickets mAdapterForMyTickets;
    ArrayList<GetTicketsModels> bookings_list = new ArrayList<>();
    TextView mEmpty_card_tv_myTickets;
    SharedPreferences preferences;
    String user_id_str;
    ProgressBar progressBar;

    public static MyTickets newInstance() {

        return new MyTickets();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_tickets, container, false);
        mMyTicket_rv = v.findViewById(R.id.my_tickets_frag_rv);
        mEmpty_card_tv_myTickets = v.findViewById(R.id.empty_tv_my_tickets_frag);
        progressBar = v.findViewById(R.id.progress_my_tickets_frag);
        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id_str = preferences.getString("id", "");

        getBookingTickets(user_id_str);

        return v;
    }

    private void getBookingTickets(final String passenger_id) {
        String tag_str_req = "req_get_bookings";

        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_BOOKING + "?passenger_id=" + passenger_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("bookings");

                        if (jsonArray.length() == 0) {
                            mEmpty_card_tv_myTickets.setVisibility(View.VISIBLE);
                            mMyTicket_rv.setVisibility(View.GONE);

                        } else {
                            mEmpty_card_tv_myTickets.setVisibility(View.GONE);
                            mMyTicket_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                bookings_list.add(new GetTicketsModels(jsonObject.getInt("booking_id"),
                                        jsonObject.getInt("ladies_seats"),
                                        jsonObject.getInt("status"),
                                        jsonObject.getInt("total_seats"),
                                        jsonObject.getInt("total_amount"),
                                        jsonObject.getInt("route_id"),
                                        jsonObject.getInt("ac_status"),
                                        jsonObject.getString("date"),
                                        jsonObject.getString("booking_date"),
                                        jsonObject.getString("arrival_time"),
                                        jsonObject.getString("route_name"),
                                        jsonObject.getString("departure_time")));
                            }
                        }
                        mAdapterForMyTickets = new AdapterForMyTickets(bookings_list, getActivity());
                        mMyTicket_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        mMyTicket_rv.setAdapter(mAdapterForMyTickets);

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
                        Log.e("Volley Error:", error.getMessage());
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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