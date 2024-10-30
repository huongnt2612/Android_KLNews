package com.appcxs.androidcvs.api.api;

import com.appcxs.androidcvs.api.model.respon.History;
import com.appcxs.androidcvs.api.model.respon.LeastOrFavoriteNews;
import com.appcxs.androidcvs.api.model.respon.ListNews;
import com.appcxs.androidcvs.api.model.respon.SaveNewsWatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {
    @GET(Api.API_GET_ALL_NEWS)
    Call<ListNews> getAllNews();

    @GET(Api.API_GET_NEWS_BY_CATEGORY)
    Call<ListNews> getNewsByCategory(@Path("categoryId") String categoryId);

    @GET(Api.API_SAVE_NEWS_WATCH)
    Call<SaveNewsWatch> saveNewsWatch(@Path("newsId") int id, @Header("Authorization") String authorization);

    @GET(Api.API_HISTORY)
    Call<History> getHistory(@Header("Authorization") String authorization);

    @GET(Api.API_SEARCH)
    Call<ListNews> searchNews(@Query("key") String key);

    @GET(Api.API_GET_LEAST_NEWS)
    Call<LeastOrFavoriteNews>getLeastNews();

    @GET(Api.API_GET_FAVORITE_NEWS)
    Call<LeastOrFavoriteNews>getFavoriteNews();
}
