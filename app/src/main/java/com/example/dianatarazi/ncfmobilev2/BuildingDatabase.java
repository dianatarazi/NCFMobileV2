package com.example.dianatarazi.ncfmobilev2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

public abstract class BuildingDatabase extends RoomDatabase {

    private static BuildingDatabase instance;

    public abstract BuildingDao buildingDao();
}
