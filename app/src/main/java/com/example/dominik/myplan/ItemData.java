package com.example.dominik.myplan;

import android.media.Image;

public class ItemData {
    private String title;
    private int imageUrl;
    private boolean isChild;

    public ItemData(String title, int imageUrl, boolean isChild) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.isChild = isChild;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public boolean isChild() {
        return isChild;
    }
}
