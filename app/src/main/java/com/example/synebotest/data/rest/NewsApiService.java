package com.example.synebotest.data.rest;

import com.example.synebotest.data.model.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Observable<NewsResponse> getNews(@Query("country") String country, @Query("apiKey") String key);
}