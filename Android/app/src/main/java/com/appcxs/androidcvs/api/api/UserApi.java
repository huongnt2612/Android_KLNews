package com.appcxs.androidcvs.api.api;

import com.appcxs.androidcvs.api.model.User;
import com.appcxs.androidcvs.api.model.respon.UserLogin;
import com.appcxs.androidcvs.api.model.respon.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST(Api.API_LOGIN)
    Call<UserLogin> UserLogin(@Body User user);
    @POST(Api.API_SIGN_IN)
    Call<UserRegister> UserRegister(@Body User user);
}
