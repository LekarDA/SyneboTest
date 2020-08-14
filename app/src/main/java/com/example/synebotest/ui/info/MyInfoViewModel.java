package com.example.synebotest.ui.info;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MyInfoViewModel extends ViewModel {

    private MutableLiveData<String> data = new MutableLiveData<>();
    private DisposableObserver observer;

    @ViewModelInject
    public MyInfoViewModel(){}

    public LiveData<String> getDate(){

        observer = new DisposableObserver<String>() {
            @Override public void onNext(String s) {
                data.setValue(Calendar.getInstance().getTime().toString());
            }
            @Override public void onComplete() { }
            @Override public void onError(Throwable t) { }
        };

        Observable<String> observable = Observable.just(Calendar.getInstance().getTime().toString())
                .delay(1000, TimeUnit.MILLISECONDS)
                .repeat()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(observer);
        return  data;
    }

    public void dispose(){
        observer.dispose();
        Log.e("@@@","onDispose");
    }
}