package com.example.dominik.myplan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AddUebungenActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        ArrayList<String> TitleArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.muskelgruppen)));
        ArrayList<ItemData> mDataset = new ArrayList<>();
        for(int i = 0; i < TitleArray.size(); i++) {
            mDataset.add(new ItemData(TitleArray.get(i), R.drawable.muscles_chest));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(adapter);
    }
}
