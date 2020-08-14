package com.example.synebotest.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.synebotest.data.model.ArticleDB;

@Database(entities = {ArticleDB.class},version = 2,exportSchema = false)
public abstract class NewsDB extends RoomDatabase {
    public abstract NewsDao newsDao();
}