package com.example.dianatarazi.ncfmobilev2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingHolder> {
    private ArrayList<Building> buildings = new ArrayList<>();
    private static String TAG = "BuildingAdapter";
    private OnBuildingListener mOnBuildingListener;

    public BuildingAdapter(ArrayList<Building> buildings) {
        //buildings = new ArrayList<>();
        this.buildings = buildings;
        //this.mOnBuildingListener = onBuildingListener;
    }

    @NonNull
    @Override
    public BuildingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.map_list_item, parent, false);
        //Toast.makeText(parent.getContext(), "size is: " + size, Toast.LENGTH_SHORT).show();
        return new BuildingHolder(itemView, mOnBuildingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingHolder holder, int position) {
        if (!buildings.isEmpty()) {
            Building currentItem = buildings.get(position);
            holder.textViewName.setText(currentItem.getName());
            //Log.d(TAG, currentItem.getBuildingName());

        }
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
        notifyDataSetChanged();
    }

    public class BuildingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        OnBuildingListener onBuildingListener;

        public BuildingHolder(@NonNull View itemView, OnBuildingListener onBuildingListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_building_name);
            this.onBuildingListener = onBuildingListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onBuildingListener.onBuildingClick(getAdapterPosition());
        }
    }

    public interface OnBuildingListener {
        void onBuildingClick(int position);
    }

    public void setOnBuildingListener(OnBuildingListener listener) {
        mOnBuildingListener = listener;
    }
}
