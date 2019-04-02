package com.example.dianatarazi.ncfmobilev2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;


public interface BuildingDao {

    LiveData<Building> getBuilding(int buildingNum);
}
