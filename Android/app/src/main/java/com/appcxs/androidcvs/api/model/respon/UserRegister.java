package com.appcxs.androidcvs.api.model.respon;

import com.appcxs.androidcvs.api.model.User;

import java.io.Serializable;

public class UserRegister implements Serializable {
    private String status;
    private User data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
