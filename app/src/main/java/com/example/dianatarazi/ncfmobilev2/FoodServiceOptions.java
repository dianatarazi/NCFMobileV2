package com.example.dianatarazi.ncfmobilev2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FoodServiceOptions extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_service_options, container, false);

        ImageView boarsHeadBtn = view.findViewById(R.id.boars_btn);
        ImageView metzCafeBtn = view.findViewById(R.id.metz_cafe_btn);
        ImageView fourWindsBtn = view.findViewById(R.id.fourwinds_btn);

        return view;
    }

}
