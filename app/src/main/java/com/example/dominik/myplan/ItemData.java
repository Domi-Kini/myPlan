package com.example.dominik.myplan;

import android.media.Image;

public class ItemData {
    public static final int GROUP = 0;
    public static final int UEBUNG = 1;
    public static final int PLAN = 2;

    private String title;
    private int imageUrl;
    private int type;

    public ItemData(String title, int imageUrl, int type) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public int getType() {
        return type;
    }
}
