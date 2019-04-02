package com.example.dianatarazi.ncfmobilev2;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract AnnouncementList contributeAnnouncementList();

    @ContributesAndroidInjector
    abstract AnnouncementDetail contributeAnnouncementDetail();
}
