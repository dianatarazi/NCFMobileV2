package com.example.dianatarazi.ncfmobilev2;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {
    private final String TAG = "MapViewModel";
    private final MutableLiveData<Building> selected = new MutableLiveData<Building>();
    private ArrayList<Building> buildings = new ArrayList<>();
    private DatabaseReference reference;

    public MapViewModel() {
        //this.buildings = buildings;
    }

    public void fetchBuildings() {
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
                    buildings.add(building);
                    //Log.d(TAG, "buildings : " + building.getLatitude() + building.getLongitude());
                }

                //adapter.setBuildings(buildingList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //return buildings;

    }

    public void select(Building item) {
        selected.setValue(item);
    }

    public LiveData<Building> getSelected() {

        return selected;
    }
}
