package com.example.synebotest.ui.list;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.synebotest.data.model.Article;
import com.example.synebotest.data.model.ArticleDB;
import com.example.synebotest.data.model.NewsResponse;
import com.example.synebotest.data.rest.NewsRepository;
import com.example.synebotest.data.util.MapData;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    private static final String TAG = "NewsViewModel";
    private NewsRepository repository;
    private MutableLiveData<ArrayList<ArticleDB>> newsList = new MutableLiveData<>();
    private Disposable disposable;

    @ViewModelInject
    public NewsViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<ArticleDB>> getNewsList() {
        return newsList;
    }

    public void fetchData() {
                disposable = Observable.interval(0,5000,TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::newsResponce,this::onError);
    }

    private void newsResponce(Long aLong) {
        Observable<NewsResponse> observable = repository.getNews();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.getArticles())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.e("TAG", "onHandleError" + throwable.getMessage());
    }

    private void handleResults(ArrayList<Article> articles) {
        ArrayList<ArticleDB> news = MapData.mapNewsList(articles);
        newsList.setValue(news);
    }

    private void onError(Throwable throwable) {
        Log.e("TAG", "onError" + throwable.getMessage());
    }

    public void saveData() {
        if (newsList.getValue() != null)
            repository.insertNews(newsList.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> Log.i(TAG, "insert success"));
    }

    public void dispose() {
        disposable.dispose();
        Log.e("TAG", "onDispose");
    }
}