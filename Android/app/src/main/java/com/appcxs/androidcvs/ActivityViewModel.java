package com.appcxs.androidcvs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcxs.androidcvs.api.api.ApiModule;
import com.appcxs.androidcvs.api.model.User;
import com.appcxs.androidcvs.api.model.respon.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityViewModel extends ViewModel {
    public UserLogin userLogin;
    public MutableLiveData<Boolean> isLogin = new MutableLiveData<>(false);

    public void login(User user) {
        ApiModule.userApi.UserLogin(user).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()) {

                    userLogin = response.body();
                    isLogin.postValue(true);
                } else {

                    int statusCode = response.code();
                    isLogin.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                isLogin.postValue(false);
            }

        });
    }
}
