package com.example.dianatarazi.ncfmobilev2;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnnouncementViewModel extends ViewModel {
    private LiveData<List<Announcement>> announcements;
    private AnnouncementRepository announcementRepo;
    private MutableLiveData<Announcement> selected = new MutableLiveData<>();

    @Inject
    public AnnouncementViewModel(AnnouncementRepository announcementRepo) {
        this.announcementRepo = announcementRepo;
    }

    public void init() {
        if (this.announcements != null) {
            return;
        }

        announcements = announcementRepo.getAnnouncements();
    }

    public LiveData<List<Announcement>> getAnnouncements() {
        return this.announcements;
    }

    public void select(Announcement item) {
        selected.setValue(item);
    }

    public LiveData<Announcement> getSelected() {
        return selected;
    }

}
