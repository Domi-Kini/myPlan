package com.example.dominik.myplan;

public class Satz {
    private int gewicht;
    private int wiederholungen;

    public Satz(int gewicht, int wh) {
        this.gewicht = gewicht;
        this.wiederholungen = wh;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setWiederholungen(int wh) {
        this.wiederholungen = wh;
    }

    public int getWiederholungen() {
        return wiederholungen;
    }
}
