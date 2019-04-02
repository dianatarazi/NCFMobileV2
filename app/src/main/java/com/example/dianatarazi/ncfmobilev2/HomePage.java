package com.example.dianatarazi.ncfmobilev2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomePage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        Button announcementsBtn = view.findViewById(R.id.main_announcement_btn);
        Button mapBtn = view.findViewById(R.id.main_map_btn);
        Button foodBtn = view.findViewById(R.id.main_food_btn);
        Button contactsBtn = view.findViewById(R.id.main_contacts_btn);

        announcementsBtn.setOnClickListener(view1 -> {
            Intent announcementIntent = new Intent(getActivity(), AnnouncementActivity.class);
            startActivity(announcementIntent);
        });

        foodBtn.setOnClickListener(view12 -> {
            Intent foodIntent = new Intent(getActivity(), FoodActivity.class);
            startActivity(foodIntent);
        });

        mapBtn.setOnClickListener(view13 -> {
            Intent mapIntent = new Intent(getActivity(), MapActivity.class);
            startActivity(mapIntent);
        });

        contactsBtn.setOnClickListener(view14 -> {
            Intent contactsIntent = new Intent(getActivity(), ContactsActivity.class);
            startActivity(contactsIntent);
        });

        return view;
    }
}
