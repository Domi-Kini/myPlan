package com.example.dominik.myplan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AddUebungenActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        ArrayList<ItemData> mDataset = new ArrayList<>();
        addAllImages(mDataset);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void addAllImages(ArrayList<ItemData> mDataset) {
        ArrayList<String> TitleArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.muskelgruppen)));
        mDataset.add(new ItemData(TitleArray.get(0), R.drawable.mucles_chest));
        mDataset.add(new ItemData(TitleArray.get(1), R.drawable.mucles_back));
        mDataset.add(new ItemData(TitleArray.get(2), R.drawable.mucles_legs));
        mDataset.add(new ItemData(TitleArray.get(3), R.drawable.mucles_biceps));
        mDataset.add(new ItemData(TitleArray.get(4), R.drawable.mucles_triceps));
        mDataset.add(new ItemData(TitleArray.get(5), R.drawable.mucles_shoulders));
        mDataset.add(new ItemData(TitleArray.get(6), R.drawable.mucles_abdominals));
    }
}
