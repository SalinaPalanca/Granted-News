package com.example.newsgranted;

import com.example.newsgranted.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("Top-headlines")
    Call<Headlines> getHeadlines(
        @Query("Country") String country,
        @Query("apikey") String apikey

    );
}
