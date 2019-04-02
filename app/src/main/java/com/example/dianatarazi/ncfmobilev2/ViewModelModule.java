package com.example.dianatarazi.ncfmobilev2;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnnouncementViewModel.class)
    abstract ViewModel bindAnouncementViewModel(AnnouncementViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
