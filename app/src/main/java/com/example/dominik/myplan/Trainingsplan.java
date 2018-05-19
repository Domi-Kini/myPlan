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
        e.setID(uebungen.size());
        uebungen.add(e);
    }
    public void deleteUebunge(int id) {
        Uebung zw;
        for (int i = id + 1; i < uebungen.size(); i++) {
            zw = uebungen.get(i);
            zw.setID(zw.getID() - 1);
        }
        uebungen.remove(id);
    }
    public void setTag(int tag) {
        if (tag < 1 | tag > 7)
            return; //HIER MUSS NOCH WAS GEMACHT WERDEN!!
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
        }
    }
}
