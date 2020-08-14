package com.example.synebotest.data.rest;

import com.example.synebotest.data.db.NewsDao;
import com.example.synebotest.data.model.ArticleDB;
import com.example.synebotest.data.model.NewsResponse;
import com.example.synebotest.util.Constants;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class NewsRepository {
    private NewsApiService newsApiService;
    private NewsDao newsDao;

    @Inject
    public NewsRepository(NewsApiService newsApiService, NewsDao newsDao) {
        this.newsApiService = newsApiService;
        this.newsDao = newsDao;
    }

    public Observable<NewsResponse> getNews(){
        return newsApiService.getNews(Constants.COUNTRY,Constants.API_KEY);
    }

    public Completable insertNews(List<ArticleDB> news){
         return newsDao.insertNews(news);
    }

    public Single<ArticleDB> getArticleById(int id){
         return  newsDao.getUserById(id);
    }
}