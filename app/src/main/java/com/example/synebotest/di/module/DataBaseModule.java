package com.example.synebotest.di.module;

import android.app.Application;
import androidx.room.Room;
import com.example.synebotest.data.db.NewsDB;
import com.example.synebotest.data.db.NewsDao;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static NewsDB provideNewsDB(Application application){
        return Room.databaseBuilder(application, NewsDB.class,"News Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static NewsDao provideNewsDao(NewsDB newsDB){return newsDB.newsDao();}
}