package com.example.demo.service.model;

public class SearchModel {
    public static final String EXACT = "EXACT";
    public static final String CONTAINS = "CONTAINS";
    private String author;
    private String camera;
    private String tags;

    private String searchTypeAuthor;
    private String searchTypeCamera;
    private String searchTypeTags;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSearchTypeAuthor() {
        return searchTypeAuthor;
    }

    public void setSearchTypeAuthor(String searchTypeAuthor) {
        this.searchTypeAuthor = searchTypeAuthor;
    }

    public String getSearchTypeCamera() {
        return searchTypeCamera;
    }

    public void setSearchTypeCamera(String searchTypeCamera) {
        this.searchTypeCamera = searchTypeCamera;
    }

    public String getSearchTypeTags() {
        return searchTypeTags;
    }

    public void setSearchTypeTags(String searchTypeTags) {
        this.searchTypeTags = searchTypeTags;
    }
}
