package com.example.dianatarazi.ncfmobilev2;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AnnouncementDao {

    @Insert(onConflict = REPLACE)
    void save(List<Announcement> announcements);

    @Query("SELECT * FROM announcement")
    LiveData<List<Announcement>> load();

    @Query("SELECT * FROM announcement WHERE lastRefresh > :lastRefreshMax LIMIT 1")
    List<Announcement> hasAnnouncement(Date lastRefreshMax);
}
