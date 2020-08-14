package com.example.synebotest.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.synebotest.data.model.ArticleDB;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NewsDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    Completable insertNews(List<ArticleDB> news);

    @Query("SELECT * FROM news_table WHERE id = :newsId")
    Single<ArticleDB> getUserById(int newsId);
}