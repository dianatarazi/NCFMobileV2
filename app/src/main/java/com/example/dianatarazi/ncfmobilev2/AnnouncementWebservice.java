package com.example.dianatarazi.ncfmobilev2;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnnouncementWebservice {
    @GET("announcements")
    Call<List<Announcement>> getAnnouncements();

    @GET("announcement/{announcement}")
    Call<Announcement> getAnnouncement();
}
