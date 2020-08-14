package com.example.synebotest.ui.detail;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.synebotest.data.model.ArticleDB;
import com.example.synebotest.data.rest.NewsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends ViewModel {

    private NewsRepository repository;
    private MutableLiveData<ArticleDB> article = new MutableLiveData<>();

    @ViewModelInject
    public DetailViewModel(NewsRepository repository){
        this.repository = repository;
    }

    public MutableLiveData<ArticleDB> fetchArticleById(int id){
        repository.getArticleById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ArticleDB>() {
                    @Override
                    public void onSuccess(ArticleDB articleDB) {
                        article.setValue(articleDB);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Detail viewmodel", "row empty");
                    }
                });
        return article;
    }
}