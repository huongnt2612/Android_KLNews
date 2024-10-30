package com.appcxs.androidcvs.api.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface ApiModule {
    Gson gson = new GsonBuilder().create();

    UserApi userApi = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(Api.okHttpClient())
            .build()
            .create(UserApi.class);
    CategoryApi categoryApi = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(Api.okHttpClient())
            .build()
            .create(CategoryApi.class);
    NewsApi newsApi = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(Api.okHttpClient())
            .build()
            .create(NewsApi.class);
}
