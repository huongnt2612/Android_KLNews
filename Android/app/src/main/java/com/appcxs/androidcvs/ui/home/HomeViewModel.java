package com.appcxs.androidcvs.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcxs.androidcvs.api.api.Api;
import com.appcxs.androidcvs.api.api.ApiModule;
import com.appcxs.androidcvs.api.model.Category;
import com.appcxs.androidcvs.api.model.respon.LeastOrFavoriteNews;
import com.appcxs.androidcvs.api.model.respon.ListCategory;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.api.model.respon.ListNews;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public ArrayList<News> getListNews() {
        return listNews;
    }

    public void setListNews(ArrayList<News> listNews) {
        this.listNews = listNews;
    }

    public ArrayList<News> getListNewsByCategory() {
        return listNewsByCategory;
    }

    public void setListNewsByCategory(ArrayList<News> listNewsByCategory) {
        this.listNewsByCategory = listNewsByCategory;
    }

    private ArrayList<Category> category = new ArrayList<>();
    public MutableLiveData<Boolean> isGetCategory = new MutableLiveData<>(false);

    private ArrayList<News> listNews = new ArrayList<>();
    public MutableLiveData<Boolean> isGetNews = new MutableLiveData<>(false);

    private ArrayList<News> listNewsByCategory = new ArrayList<>();


    public MutableLiveData<Boolean> isGetNewsByCategory = new MutableLiveData<>(false);

    public void getNewsByCategory(int id) {
        ApiModule.newsApi.getNewsByCategory(String.valueOf(id)).enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if (response.body() != null && response.isSuccessful()) {
                    listNewsByCategory.clear();
                    listNewsByCategory.addAll(response.body().getData().getNewsList());
                    isGetNewsByCategory.postValue(true);
                } else {
                    isGetNewsByCategory.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                isGetNewsByCategory.postValue(false);
            }
        });
    }

    public void getAllCategory() {
        ApiModule.categoryApi.getAllCategory().enqueue(new Callback<ListCategory>() {
            @Override
            public void onResponse(Call<ListCategory> call, Response<ListCategory> response) {
                if (response.body() != null && response.isSuccessful()) {
                    category.clear();
                    category.addAll(response.body().getData());
                    isGetCategory.postValue(true);
                } else {
                    isGetCategory.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ListCategory> call, Throwable t) {
                isGetCategory.postValue(false);
            }
        });
    }

    public void getAllNews() {
        ApiModule.newsApi.getAllNews().enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if (response.body() != null && response.isSuccessful()) {
                    response.body().getData();
                    listNews.clear();
                    listNews.addAll(response.body().getData().getPageData());

                    isGetNews.postValue(true);
                } else {
                    isGetNews.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                isGetNews.postValue(false);
            }
        });
    }
    public void getFavoriteNews() {
        ApiModule.newsApi.getFavoriteNews().enqueue(new Callback<LeastOrFavoriteNews>() {
            @Override
            public void onResponse(Call<LeastOrFavoriteNews> call, Response<LeastOrFavoriteNews> response) {
                if (response.body() != null && response.isSuccessful()) {
                    response.body().getData();
                    listNews.clear();
                    listNews.addAll(response.body().getData());

                    isGetNews.postValue(true);
                } else {
                    isGetNews.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<LeastOrFavoriteNews> call, Throwable t) {
                isGetNews.postValue(false);
            }
        });
    }
    public void getLeastNews() {
        ApiModule.newsApi.getLeastNews().enqueue(new Callback<LeastOrFavoriteNews>() {
            @Override
            public void onResponse(Call<LeastOrFavoriteNews> call, Response<LeastOrFavoriteNews> response) {
                if (response.body() != null && response.isSuccessful()) {
                    response.body().getData();
                    listNews.clear();
                    listNews.addAll(response.body().getData());

                    isGetNews.postValue(true);
                } else {
                    isGetNews.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<LeastOrFavoriteNews> call, Throwable t) {
                isGetNews.postValue(false);
            }
        });
    }
    public void search(String text) {
        ApiModule.newsApi.searchNews(text).enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if((response.body() != null && response.isSuccessful() && response.body().getData()!=null)) {
                    listNews.clear();
                    listNews.addAll(response.body().getData().getPageData());
                    isGetNews.postValue(true);
                }
                else
                    isGetNews.postValue(false);
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                isGetNews.postValue(false);
            }
        });
    }
}
