package com.example.dianatarazi.ncfmobilev2;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    //database injection

    @Provides
    @Singleton
    AnnouncementDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                AnnouncementDatabase.class, "AnnouncementDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    AnnouncementDao provideAnnouncementDao(AnnouncementDatabase database) {
        return database.announcementDao();
    }

    //Repository injection

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    AnnouncementRepository provideAnnouncementRepository(AnnouncementWebservice webservice, AnnouncementDao announcementDao, Executor executor) {
        return new AnnouncementRepository(webservice, announcementDao, executor);
    }


    //network injection

    private static String BASE_URL = "https://news.ncf.edu/wp-json/wp/v2/";


    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
       Retrofit retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create(gson))
               .baseUrl(BASE_URL)
               .build();
       return retrofit;
    }

    @Provides
    @Singleton
    AnnouncementWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(AnnouncementWebservice.class);
    }
}
