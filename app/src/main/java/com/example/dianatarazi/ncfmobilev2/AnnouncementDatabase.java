package com.example.dianatarazi.ncfmobilev2;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Announcement.class}, version = 1)
@TypeConverters({DateConverter.class, MapConverter.class})
public abstract class AnnouncementDatabase extends RoomDatabase {

    //singleton
    private static volatile AnnouncementDatabase INSTANCE;

    //Dao
    public abstract AnnouncementDao announcementDao();


}
