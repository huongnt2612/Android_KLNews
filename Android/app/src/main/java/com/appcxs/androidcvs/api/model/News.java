package com.appcxs.androidcvs.api.model;

import com.appcxs.androidcvs.api.model.Category;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String title;
    private String content;
    private String author;
    private String description;
    private String thumbnail;
    private String createDate;
    private Category data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Category getData() {
        return data;
    }

    public void setData(Category data) {
        this.data = data;
    }
}
