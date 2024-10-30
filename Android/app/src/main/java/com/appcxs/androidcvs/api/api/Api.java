package com.appcxs.androidcvs.api.api;

import com.appcxs.androidcvs.Constance;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Api {
    //
    public static final String BASE_URL = "https://5c94-2405-4803-fe33-7570-5d6e-be2f-b078-2ff8.ngrok-free.app" + "/api/v1/";
    public static final String API_LOGIN ="no-auth/login";
    public static  final String API_SIGN_IN ="no-auth/create-user";
    public static  final  String API_GET_ALL_CATEGORY="no-auth/search-category";
    public static final  String API_GET_ALL_NEWS ="no-auth/search-all_news";
    public static final  String API_GET_NEWS_BY_CATEGORY ="no-auth/filter-new/{categoryId}";
    public static final  String API_SAVE_NEWS_WATCH ="no-auth/save-news-watched/{newsId}";
    public static final  String API_HISTORY ="user/get-all-news-watched";
    public static final String API_GET_FAVORITE_NEWS ="no-auth/favorite-new";
    public static final String API_GET_LEAST_NEWS = "no-auth/least-new";
    public static final String API_SEARCH ="no-auth/search-news";
    public static final String BEARER ="Bearer ";
    public static OkHttpClient okHttpClient() {
        HttpLoggingInterceptor h = new HttpLoggingInterceptor();
        h.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(h)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
}
