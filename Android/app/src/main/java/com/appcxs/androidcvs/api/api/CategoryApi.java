package com.appcxs.androidcvs.api.api;

import com.appcxs.androidcvs.api.model.respon.ListCategory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {
    @GET(Api.API_GET_ALL_CATEGORY)
    Call<ListCategory> getAllCategory();
}
