package com.appcxs.androidcvs.api.model.respon;

import com.appcxs.androidcvs.api.model.News;

import java.io.Serializable;
import java.util.ArrayList;

public class LeastOrFavoriteNews implements Serializable {
    String status;
    ArrayList<News> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }
}
