package com.appcxs.androidcvs.ui.home.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcxs.androidcvs.Constance;
import com.appcxs.androidcvs.api.api.Api;
import com.appcxs.androidcvs.api.api.ApiModule;
import com.appcxs.androidcvs.api.model.News;
import com.appcxs.androidcvs.api.model.respon.History;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryViewModel extends ViewModel {

    public ArrayList<News> listNews = new ArrayList<>();
    public MutableLiveData<Boolean> isGetHistory = new MutableLiveData<>(false);

    public void getHistory(String token) {
        ApiModule.newsApi.getHistory(Api.BEARER + token).enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                if (response.body() != null && response.isSuccessful()) {
                    listNews.clear();
                    listNews.addAll(response.body().getData());
                    isGetHistory.postValue(true);
                } else {
                    isGetHistory.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                isGetHistory.postValue(false);
            }
        });
    }
}
