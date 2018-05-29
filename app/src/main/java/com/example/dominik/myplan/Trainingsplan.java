package com.example.dominik.myplan;

import java.util.*;

public class Trainingsplan {
    private String name;
    private List<Uebung> uebungen;
    private int tag;

    public Trainingsplan(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void setUebung(Uebung e) {
        if (uebungen == null)
            uebungen = new ArrayList<Uebung>();
        uebungen.add(e);
    }

    public void deleteUebunge(int ind) {
        uebungen.remove(ind);
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTag() {
        switch (tag) {
            case 1: return "Montag";
            case 2 : return "Dienstag";
            case 3: return "Mittwoch";
            case 4: return "Donnerstag";
            case 5: return "Freitag";
            case 6: return "Samstag";
            case 7: return "Sonntag";
            default: return "kein Tag ausgewaehlt";
        }
    }
}
