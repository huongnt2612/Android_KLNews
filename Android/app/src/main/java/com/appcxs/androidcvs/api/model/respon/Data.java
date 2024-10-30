package com.appcxs.androidcvs.api.model.respon;

import com.appcxs.androidcvs.api.model.News;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    private ArrayList<News> pageData;

    private ArrayList<News> newsList;

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public ArrayList<News> getPageData() {
        return pageData;
    }

    public void setPageData(ArrayList<News> pageData) {
        this.pageData = pageData;
    }
}
