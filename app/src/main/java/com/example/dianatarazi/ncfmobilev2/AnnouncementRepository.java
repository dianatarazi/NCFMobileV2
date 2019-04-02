package com.example.dianatarazi.ncfmobilev2;

import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AnnouncementRepository {
    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final AnnouncementWebservice webservice;
    private final AnnouncementDao announcementDao;
    private final Executor executor;

    @Inject
    public AnnouncementRepository(AnnouncementWebservice webservice, AnnouncementDao announcementDao, Executor executor) {
        this.webservice = webservice;
        this.announcementDao = announcementDao;
        this.executor = executor;
    }

    public LiveData<List<Announcement>> getAnnouncements() {
        refreshAnnouncements(); //refreshes data from rest api if possible
        //Toast.makeText(App.context, "getAnnouncements is executed from repo", Toast.LENGTH_SHORT).show();

        return announcementDao.load(); //return LiveData from the database
    }


    private void refreshAnnouncements() {
        executor.execute(() -> {
            //check if announcements were fetched recently
            boolean annonsExist = (announcementDao.hasAnnouncement(getMaxRefreshTime(new Date())) != null);
            //if announcements have to be updated from the internet
            if (!annonsExist) {
                webservice.getAnnouncements().enqueue(new Callback<List<Announcement>>() {
                    @Override
                    public void onResponse(Call<List<Announcement>> call, Response<List<Announcement>> response) {
                        Toast.makeText(App.context, "Data refreshed from internet", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            List<Announcement> announcements = response.body();
                            //maybe change for loop
                            for (Announcement annon : announcements) {
                                annon.setLastRefresh(new Date());
                            }
                            announcementDao.save(announcements);


                        });
                    }

                    @Override
                    public void onFailure(Call<List<Announcement>> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(App.context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(App.context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }



        });
    }


    private Date getMaxRefreshTime(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return calendar.getTime();
    }
}
