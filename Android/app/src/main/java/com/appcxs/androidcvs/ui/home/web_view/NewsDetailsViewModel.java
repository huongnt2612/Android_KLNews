package com.appcxs.androidcvs.ui.home.web_view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.api.api.Api;
import com.appcxs.androidcvs.api.api.ApiModule;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.api.model.respon.SaveNewsWatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsViewModel extends ViewModel {
    private News news = new News();

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public MutableLiveData<Boolean> isSaveNewsWatch = new MutableLiveData<>(false);

    public void saveNewsWatch(String token) {
        ApiModule.newsApi.saveNewsWatch(news.getId(), Api.BEARER+token).enqueue(new Callback<SaveNewsWatch>() {
            @Override
            public void onResponse(Call<SaveNewsWatch> call, Response<SaveNewsWatch> response) {
                if(response.body()!=null && response.isSuccessful()) {
                    isSaveNewsWatch.postValue(true);
                }
                else  {
                    isSaveNewsWatch.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<SaveNewsWatch> call, Throwable t) {
                isSaveNewsWatch.postValue(false);
            }
        });
    }
}
