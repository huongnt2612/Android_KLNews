package com.appcxs.androidcvs.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcxs.androidcvs.api.api.ApiModule;
import com.appcxs.androidcvs.api.model.User;
import com.appcxs.androidcvs.api.model.respon.UserRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    UserRegister userRegister;
    MutableLiveData<Boolean> isRegister = new MutableLiveData<>(false);

    public void register(User user) {
        ApiModule.userApi.UserRegister(user).enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                if (response.isSuccessful()) {
                    // Mã trạng thái là 200 OK
                    userRegister = response.body();
                    isRegister.postValue(true);
                } else {
                    // Mã trạng thái không phải là 200, xử lý lỗi tại đây
                    int statusCode = response.code();
                    isRegister.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                isRegister.postValue(false);
            }

        });
    }
}
