package com.example.dominik.myplan;

import java.util.*;

public class Uebung {
    private String name;
    private List<Integer> wiederholungen;
    private int saetze;
    private List<Integer> gewicht;

    public Uebung(String name, int saetze, int[] gewicht, int[] wh) {
        this.name = name;
        this.gewicht = new ArrayList<>();
        wiederholungen = new ArrayList<>();
        for(int i = 0; i < saetze; i++) {
            this.gewicht.add(gewicht[i]);
            wiederholungen.add(wh[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setGewicht(int satz, int gw) {
        if (gewicht == null)
            gewicht = new ArrayList<>();
        gewicht.set(satz - 1, gw);
    }

    public void setWH(int satz, int wh) {
        if (wiederholungen == null)
            wiederholungen = new ArrayList<>();
        wiederholungen.set(satz - 1, wh);
    }

    public void removeSatz(int satz) {
        saetze -= 1;
        gewicht.remove(satz - 1);
        wiederholungen.remove(satz - 1);
    }

    public void addSatz(int gewicht, int wh) {
        saetze += 1;
        this.gewicht.add(gewicht);
        this.gewicht.add(wh);
    }

    public int getGewicht(int satz) {
        return gewicht.get(satz - 1);
    }
    public int getWH(int satz) {
        return gewicht.get(satz - 1);
    }
    public int getSaetze() {
        return saetze;
    }

}
