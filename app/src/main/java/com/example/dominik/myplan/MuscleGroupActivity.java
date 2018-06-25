package com.example.dominik.myplan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MuscleGroupActivity extends AppCompatActivity {
    public static final int RESULT_FINISH = 0;

    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    MySingleton singleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        TextView textTitle = (TextView) findViewById(R.id.text_uebung_title);
        textTitle.setText("Muskelgruppen");

        ArrayList<ItemData> mDataset = new ArrayList<>();
        addAllImages(mDataset);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mAdapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button createPlanButton = (Button) findViewById(R.id.button_create_plan);
        createPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (singleton.getRightPlan().getUebungen() != null) {
                    finish();
                } else {
                    Toast toast = Toast.makeText(v.getContext(), "Bitte füge zuerst eine Übung hinzu.", Toast.LENGTH_LONG);
                    TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
                    tview.setTextColor(Color.RED);
                    tview.setGravity(Gravity.CENTER);
                    toast.show();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.button_cancel_plan);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleton.getPlans().remove(singleton.getIndex());
                finish();
            }
        });
    }

    private void addAllImages(ArrayList<ItemData> mDataset) {
        ArrayList<String> TitleArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.muskelgruppen)));
        mDataset.add(new ItemData(TitleArray.get(0), R.drawable.mucles_chest, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(1), R.drawable.mucles_back, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(2), R.drawable.mucles_legs, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(3), R.drawable.mucles_biceps, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(4), R.drawable.mucles_triceps, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(5), R.drawable.mucles_shoulders, ItemData.GROUP));
        mDataset.add(new ItemData(TitleArray.get(6), R.drawable.mucles_abdominals, ItemData.GROUP));
    }
}
