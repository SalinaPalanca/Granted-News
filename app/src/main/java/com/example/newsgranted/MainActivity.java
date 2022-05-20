package com.example.newsgranted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.newsgranted.Model.Articles;
import com.example.newsgranted.Model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final String API_KEY = "6ef96dd7b2d24414b60801c38b075d68";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country = getCountry();
        retrieveJson(country,API_KEY);

    }

    public void retrieveJson(String country, String apiKey) {

        retrofit2.Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(retrofit2.Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() !=null) {
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this, articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Headlines> call, Throwable t) {

             }
        });
    }

        public String getCountry(){
            Locale locale = Locale.getDefault();
            String country = locale.getCountry();
            return country.toLowerCase();

    }
}