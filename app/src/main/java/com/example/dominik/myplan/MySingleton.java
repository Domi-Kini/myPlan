package com.example.dominik.myplan;

import java.util.ArrayList;

public class MySingleton {
    private static final MySingleton ourInstance = new MySingleton();
    private ArrayList<Trainingsplan> plaene;
    private int workingOnIndex;

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {
    }

    public void addPlan(Trainingsplan plan) {
        if (plaene == null)
            plaene = new ArrayList<>();
        plaene.add(plan);
    }

    public void setPlans(ArrayList<Trainingsplan> list) {
        plaene = list;
    }

    public Trainingsplan getRightPlan() {
        return plaene.get(workingOnIndex);
    }

    public ArrayList<Trainingsplan> getPlans() {
        return plaene;
    }

    public void setIndex(int ind) {
        workingOnIndex = ind;
    }

    public int getIndex() {
        return workingOnIndex;
    }
}
