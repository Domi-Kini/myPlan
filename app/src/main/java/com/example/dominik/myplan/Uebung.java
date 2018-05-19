package com.example.dominik.myplan;

import java.util.*;

public class Uebung {
    private int id;
    private String name;
    private List<Integer> wiederholungen;
    private int saetze;
    private List<Integer> gewicht;

    public Uebung(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }
    public void changeName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public boolean setGewicht(int satz, int gw) {
        if (gewicht == null)
            gewicht = new ArrayList<Integer>();
        if (gewicht.size() < satz) {
            if (satz - gewicht.size() == 1)
                gewicht.add(gw);
            else return false; //HIER MUSS NOCH WAS GEMACHT WERDEN!!
        } else {
            gewicht.set(satz, gw);
        }
        return true;
    }
    public boolean setWH(int satz, int wh) {
        if (wiederholungen == null)
            wiederholungen = new ArrayList<Integer>();
        if (wiederholungen.size() < satz) {
            if (satz - wiederholungen.size() == 1)
                wiederholungen.add(wh);
            else return false; //HIER MUSS NOCH WAS GEMACHT WERDEN!!
        } else {
            wiederholungen.set(satz, wh);
        }
        return true;
    }
    public void setSaetze(int saetze) {
        if (this.saetze > saetze) {
            for (int i = this.saetze; i > saetze; i--) {
                deleteGewicht(i);
                deleteWH(i);
            }
        }
        this.saetze = saetze;
    }

    public int getGewicht(int satz) {
        if (satz > gewicht.size()) {
            System.out.println("Something's wrong in getGewicht");
            return 0;
        } else {
            return gewicht.get(satz);
        }
    }
    public int getWH(int satz) {
        if (satz > wiederholungen.size()) {
            System.out.println("Something's wrong in getWH");
            return 0;
        } else {
            return gewicht.get(satz);
        }
    }
    public int getSaetze() {
        return saetze;
    }

    private void deleteGewicht(int satz) {
        if (satz > gewicht.size()) {
            System.out.println("Something's wrong int deleteGewicht");
            return;
        } else {
            gewicht.remove(satz);
        }
    }
    private void deleteWH(int satz) {
        if (satz > wiederholungen.size()) {
            System.out.println("Something's wrong int deleteWH");
            return;
        } else {
            wiederholungen.remove(satz);
        }
    }
}