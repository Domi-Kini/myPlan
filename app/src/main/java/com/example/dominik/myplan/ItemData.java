package com.example.dominik.myplan;

import android.media.Image;

public class ItemData {
    public static final int GROUP = 1;
    public static final int UEBUNG = 2;
    public static final int PLAN = 4;

    private String title;
    private int imageUrl;
    private int type;
    private boolean existingUebung;
    private int group;

    public ItemData(String title, int imageUrl, int type) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
        this.existingUebung = false;
    }

    public ItemData(String title, int imageUrl, int type, int group) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
        this.existingUebung = false;
        this.group = group;
    }

    public ItemData(String title, int imageUrl, int type, boolean existingUebung) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
        this.existingUebung = existingUebung;
        this.group = group;
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

    public boolean isExistingUebung() {
        return existingUebung;
    }

    public int getGroup() {
        return group;
    }
}
