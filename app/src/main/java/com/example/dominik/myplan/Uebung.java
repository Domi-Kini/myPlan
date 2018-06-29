package com.example.dominik.myplan;

import java.util.*;

public class Uebung {
    private String name;
    private List<Satz> saetze;
    private int iconUrl;
    private int imgUrl;

    public Uebung(String name, Satz[] saetze, int iconUrl, int imgUrl) {
        this.name = name;
        this.saetze = new ArrayList<>(Arrays.asList(saetze));
        this.iconUrl = iconUrl;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public int getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(int url) {
        iconUrl = url;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int url) {
        imgUrl = url;
    }

    public void setGewicht(int ind, int gw) {
        saetze.get(ind).setGewicht(gw);
    }

    public void setWH(int ind, int wh) {
        saetze.get(ind).setWiederholungen(wh);
    }

    public void removeSatz(int ind) {
        saetze.remove(ind);
    }

    public void addSatz(Satz satz) {
        saetze.add(satz);
    }

    public int getGewicht(int ind) {
        return saetze.get(ind).getGewicht();
    }

    public int getWH(int ind) {
        return saetze.get(ind).getWiederholungen();
    }

    public int getSaetze() {
        return saetze.size();
    }
}
