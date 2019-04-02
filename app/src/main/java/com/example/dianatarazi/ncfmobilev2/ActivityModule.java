package com.example.dianatarazi.ncfmobilev2;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AnnouncementActivity contributeAnnouncementActivity();
}
