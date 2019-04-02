package com.example.dianatarazi.ncfmobilev2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.AndroidSupportInjection;

public class AnnouncementDetail extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private AnnouncementViewModel viewModel;
    private final String TAG = "AnnouncementDetail";

    TextView titleTextView;
    TextView dateTextView;
    TextView contentTextView;

    public AnnouncementDetail() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcement_detail, container, false);

        titleTextView = view.findViewById(R.id.detail_title_textview);
        dateTextView = view.findViewById(R.id.detail_date_textview);
        contentTextView = view.findViewById(R.id.detail_content_textview);

        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.configureDagger();
        this.configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(AnnouncementViewModel.class);
        viewModel.init();
        updateUI();
    }

    private void updateUI() {
        viewModel.getSelected().observe(this, announcement -> {
            titleTextView.setText(announcement.getTitle().get("rendered"));
            dateTextView.setText(announcement.getDate());
            contentTextView.setText(announcement.getContent().get("rendered"));
        });
    }
}
