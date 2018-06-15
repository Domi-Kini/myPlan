package com.example.dominik.myplan;

import android.media.Image;

public class ItemData {
    private String title;
    private int imageUrl;

    public ItemData(String title, int imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
