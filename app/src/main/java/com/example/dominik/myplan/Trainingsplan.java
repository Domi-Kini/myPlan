package com.example.dominik.myplan;

import java.util.*;

public class Trainingsplan {
    private String name;
    private ArrayList<Uebung> uebungen;
    private String tag;

    public Trainingsplan(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUebung(Uebung e) {
        if (uebungen == null)
            uebungen = new ArrayList<>();
        uebungen.add(e);
    }

    public void deleteUebunge(int ind) {
        uebungen.remove(ind);
    }

    public ArrayList<Uebung> getUebungen() {
        return uebungen;
    }

    public Uebung getUebung(int ind) {
        return uebungen.get(ind);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        if (tag != null)
            return tag;
        return "Kein Tag ausgewählt";
    }
}
