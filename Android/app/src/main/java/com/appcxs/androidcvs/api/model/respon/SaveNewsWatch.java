package com.appcxs.androidcvs.api.model.respon;

import com.appcxs.androidcvs.api.model.News;

public class SaveNewsWatch {
    String status;
    News data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public News getData() {
        return data;
    }

    public void setData(News data) {
        this.data = data;
    }
}
