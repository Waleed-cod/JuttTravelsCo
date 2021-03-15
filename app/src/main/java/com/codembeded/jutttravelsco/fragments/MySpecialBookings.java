package com.codembeded.jutttravelsco.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.activity.Home;
import com.codembeded.jutttravelsco.activity.Login;
import com.codembeded.jutttravelsco.activity.MyBookings;
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

public class MySpecialBookings extends Fragment {

    //    private static final String TAG = MyBookings.class.getSimpleName();
    public static final String TITLE = "Special Bookings";
    RecyclerView mMySpecialBookings_rv;
    AdapterForMyBookings mAdapterForMySpecialBookings;
    ArrayList<MyBookingsModels> mMySpecialBookings_list = new ArrayList<>();
    TextView empty_card_tv_mMySpecialBookings;
    SharedPreferences preferences;
    String user_id;

//    int id = preferences.getInt("id",0);

    public static MySpecialBookings newInstance() {
        return new MySpecialBookings();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_special_bookings, container, false);

        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");

        mMySpecialBookings_rv = v.findViewById(R.id.my_special_bookings_frag_rv);
        empty_card_tv_mMySpecialBookings = v.findViewById(R.id.empty_tv_my_special_bookings_frag);
        getSpecialBooking(user_id);

        return v;
    }

    private void getSpecialBooking(final String passenger_id) {
        String tag_str_req = "req_get_special_booking";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_SPECIAL_BOOKING + "?passenger_id=" + passenger_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("1st Response:", response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("special_bookings");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            mMySpecialBookings_list.add(new MyBookingsModels(
                                    jsonObject.getString("departure"),
                                    jsonObject.getString("arrival"),
                                    jsonObject.getString("booking_date"),
                                    jsonObject.getString("booking_time"),
                                    jsonObject.getString("vehicle_number"),
                                    jsonObject.getString("vehicle_ac_status")));
                        }
                        Log.e("Data", mMySpecialBookings_list.get(0).toString());
                        mAdapterForMySpecialBookings = new AdapterForMyBookings(mMySpecialBookings_list, getActivity());
                        mMySpecialBookings_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        mMySpecialBookings_rv.setAdapter(mAdapterForMySpecialBookings);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error: ", error.getMessage());
                Toast.makeText(getContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
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