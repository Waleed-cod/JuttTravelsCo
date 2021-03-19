package com.codembeded.jutttravelsco.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import com.codembeded.jutttravelsco.activity.SignUp;
import com.codembeded.jutttravelsco.adapter.AdapterForMyBookings;
import com.codembeded.jutttravelsco.adapter.AdapterForParcels;
import com.codembeded.jutttravelsco.helperclass.AppConfig;
import com.codembeded.jutttravelsco.helperclass.AppController;
import com.codembeded.jutttravelsco.models.MyBookingsModels;
import com.codembeded.jutttravelsco.models.ParcelModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyParcels extends Fragment {

    public static final String TITLE = "My Parcels";
    RecyclerView myParcels_rv;
    AdapterForParcels mAdapterForParcels;
    ArrayList<ParcelModels> mMyParcels_list = new ArrayList<>();
    TextView empty_card_tv_mMyParcels;
    SharedPreferences preferences;
    String user_id;

    public static MyParcels newInstance() {
        return new MyParcels();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_parcels, container, false);
        myParcels_rv = v.findViewById(R.id.my_parcels_frag_rv);

        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");

        empty_card_tv_mMyParcels = v.findViewById(R.id.empty_tv_my_parcels_frag);

        getMyParcels(user_id);

        return v;
    }

    private void getMyParcels(final String passenger_id) {
        String tag_str_req = "req_get_my_parcel";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_PARCEL + "?passenger_id=" + passenger_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("parcels");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (jsonArray.length() == 0) {
                                empty_card_tv_mMyParcels.setVisibility(View.VISIBLE);
                            } else {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                mMyParcels_list.add(new ParcelModels(jsonObject.getInt("parcel_id"),
                                        jsonObject.getString("weight"),
                                        jsonObject.getString("quantity"),
                                        jsonObject.getString("receiver_name"),
                                        jsonObject.getString("receiver_contact"),
                                        jsonObject.getString("parcel_date"),
                                        jsonObject.getString("status"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("Amount")));
                            }
                        }

                        mAdapterForParcels = new AdapterForParcels(mMyParcels_list, getActivity());
                        myParcels_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        myParcels_rv.setAdapter(mAdapterForParcels);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), "Error is:" + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error:" + error.getMessage());
                Toast.makeText(getContext(), "error of volley:" + error.getMessage(), Toast.LENGTH_SHORT).show();
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