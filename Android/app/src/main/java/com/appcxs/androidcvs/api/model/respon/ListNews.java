package com.appcxs.androidcvs.api.model.respon;

import java.io.Serializable;

public class ListNews implements Serializable {
    String status;
    Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
