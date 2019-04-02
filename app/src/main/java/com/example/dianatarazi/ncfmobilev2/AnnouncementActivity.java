package com.example.dianatarazi.ncfmobilev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import android.os.Bundle;

import javax.inject.Inject;

public class AnnouncementActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        this.configureDagger();
        this.showFragment(savedInstanceState);
    }



    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    private void showFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {

            AnnouncementList fragment = new AnnouncementList();

            getSupportFragmentManager().beginTransaction().add(R.id.announcement_fragment_container,
                    fragment).commit();
        }

    }


    private void configureDagger() {
        AndroidInjection.inject(this);
    }

}
