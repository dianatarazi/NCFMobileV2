package com.example.dianatarazi.ncfmobilev2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MapListFragment extends Fragment {

    private static String TAG = "MapListFragment";
    private BuildingAdapter adapter;
    private DatabaseReference reference;
    private ArrayList<Building> buildingList;
    private RecyclerView buildingRecyclerView;
    private MapViewModel mapViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_list, container, false);


        buildingRecyclerView = view.findViewById(R.id.map_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        buildingRecyclerView.setLayoutManager(llm);
        //buildingRecyclerView.setHasFixedSize(true);
        buildingList = new ArrayList<>();
        adapter = new BuildingAdapter(buildingList);
        buildingRecyclerView.setAdapter(adapter);


        reference = FirebaseDatabase.getInstance().getReference("Buildings");
        Query query = reference.orderByChild("name");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    Building building = new Building(
                            dataSnapshot1.getValue(Building.class).getFunction(),
                            dataSnapshot1.getValue(Building.class).getId(),
                            dataSnapshot1.getValue(Building.class).getLatitude(),
                            dataSnapshot1.getValue(Building.class).getLongitude(),
                            dataSnapshot1.getValue(Building.class).getName(),
                            dataSnapshot1.getValue(Building.class).getPrefix()

                    );
                    buildingList.add(building);
                    //Log.d(TAG, "buildings : " + building.getLatitude() + building.getLongitude());
                }

                adapter.setBuildings(buildingList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oops, something went wrong. Please reload the page", Toast.LENGTH_SHORT).show();
            }
        });

        //MapFragment mapFragment = new MapFragment();
        MapActivity mapActivity = (MapActivity) getActivity();
        //mapViewModel = new MapViewModel(buildingList);
        mapViewModel = ViewModelProviders.of(getActivity()).get(MapViewModel.class);
        //buildingList = mapViewModel.fetchBuildings();
        //adapter.setBuildings(buildingList);
        adapter.setOnBuildingListener(position -> {
            mapViewModel.select(buildingList.get(position));
            mapActivity.setFragment(0);
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.map_list, mapFragment)
//                    .addToBackStack(null)
//                    .commit();

            //Log.d(TAG, "onBuildingClick: clicked");
        });



        return view;
    }

//    @Override
//    public void onBuildingClick(int position) {
//        //buildingList.get(position); //get the position of item clicked
//        Log.d(TAG, "onBuildingClick: clicked.");
//
//    }
}
