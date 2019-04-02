package com.example.dianatarazi.ncfmobilev2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class AnnouncementList extends Fragment {

    //For data
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private AnnouncementViewModel viewModel;
    private LiveData<List<Announcement>> announcementList;
    private final String TAG = "AnnouuncementList";
    private AnnouncementAdapter adapter;
    private AnnouncementDetail announcementDetail;

    public AnnouncementList() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        announcementDetail = new AnnouncementDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcement_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.announcement_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new AnnouncementAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnAnnouncementClickListener(position -> {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.announcement_fragment_container, announcementDetail);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            //Log.d(TAG, "announcement clicked");
            announcementList = viewModel.getAnnouncements();
            Announcement announcement = announcementList.getValue().get(position);
            viewModel.select(announcement);
            //Log.d(TAG, "selected announcement " + announcement.getTitle());

        });


        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    //Configuration

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(AnnouncementViewModel.class);
        viewModel.init();
        viewModel.getAnnouncements().observe(this, announcements -> updateUI(announcements));
    }

    private void updateUI(List<Announcement> announcements) {
        adapter.setAnnouncements(announcements);
    }
}
