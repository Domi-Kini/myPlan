package com.example.dominik.myplan;

import java.util.*;

public class Uebung {
    private String name;
    private List<Satz> saetze;

    public Uebung(String name, Satz[] saetze) {
        this.name = name;
        this.saetze = new ArrayList<>(Arrays.asList(saetze));
    }

    public String getName() {
        return name;
    }

    public void setGewicht(int satz, int gw) {
        saetze.get(satz - 1).setGewicht(gw);
    }

    public void setWH(int satz, int wh) {
        saetze.get(satz - 1).setWiederholungen(wh);
    }

    public void removeSatz(int satz) {
        saetze.remove(satz - 1);
    }

    public void addSatz(Satz satz) {
        saetze.add(satz);
    }

    public int getGewicht(int satz) {
        return saetze.get(satz - 1).getGewicht();
    }
    public int getWH(int satz) {
        return saetze.get(satz - 1).getWiederholungen();
    }
    public int getSaetze() {
        return saetze.size();
    }

}
