package com.codembeded.jutttravelsco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.models.HomeImageSliderModels;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AdapterForHomeSlider extends SliderViewAdapter<AdapterForHomeSlider.ViewHolder> {

    List<HomeImageSliderModels> data;
    Context ctx;

    public AdapterForHomeSlider(List<HomeImageSliderModels> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_image_slider_box,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Glide.with(viewHolder.itemView).load(data.get(position).getImgUrl()).into(viewHolder.slider_image);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {

            ImageView slider_image;

            public ViewHolder(View itemView) {
                super(itemView);

                this.slider_image = itemView.findViewById(R.id.iv_auto_image_slider);
            }
    }


}
