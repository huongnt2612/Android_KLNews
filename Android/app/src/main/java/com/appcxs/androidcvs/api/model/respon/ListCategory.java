package com.appcxs.androidcvs.api.model.respon;

import com.appcxs.androidcvs.api.model.Category;

import java.io.Serializable;
import java.util.List;

public class ListCategory implements Serializable {
    String status;
    List<Category> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}
