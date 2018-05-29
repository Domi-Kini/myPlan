package com.example.dominik.myplan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AddUebungenActivity extends AppCompatActivity {
    ListView mlView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        ArrayList<String> items = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 50; i++)
            items.add(((char) ('A' + rnd.nextInt('Z' - 'A'))) + " " +
                    Integer.toString(i));
        Collections.sort(items);


        AdapterRecyclerView mRecyclerView = new AdapterRecyclerView(this, items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.
    }
}
