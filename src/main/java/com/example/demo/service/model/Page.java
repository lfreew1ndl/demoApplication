package com.example.demo.service.model;

import java.util.ArrayList;

public class Page {
    ArrayList<Picture> pictures = new ArrayList<Picture>();
    private float page;
    private float pageCount;
    private boolean hasMore;

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public float getPage() {
        return page;
    }

    public void setPage(float page) {
        this.page = page;
    }

    public float getPageCount() {
        return pageCount;
    }

    public void setPageCount(float pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
