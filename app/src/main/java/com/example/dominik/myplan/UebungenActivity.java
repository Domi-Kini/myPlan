package com.example.dominik.myplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class UebungenActivity extends AppCompatActivity {

    private static final String TAG = "UebungenActivity";

    private ArrayList<ItemData> mDataset;
    private String mTitle;

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        getIncomingIntent();
        setDataset();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(adapter);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("muclegroup_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            mTitle = getIntent().getStringExtra("muclegroup_name");
        }
    }

    private void setDataset() {
        mDataset = new ArrayList<>();
        switch(mTitle) {
            case "Brust":
                {
                    String[] title = getResources().getStringArray(R.array.uebungen_brust);
                    for (int i = 0; i < title.length; i++) {
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_chest, true));
                    }
                }
                break;
            case "Ruecken":
                {
                    String[] title = getResources().getStringArray(R.array.uebungen_rücken);
                    for (int i = 0; i < title.length; i++) {
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_back, true));
                    }
                }
                break;
            case "Schultern":
                {
                    String[] title = getResources().getStringArray(R.array.uebungen_schultern);
                    for (int i = 0; i < title.length; i++) {
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_shoulders, true));
                    }
                }
                break;
            case "Bauch":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_bauch);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_abdominals, true));
                }
            }
                break;
            case "Bizeps":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_bizeps);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_biceps, true));
                }
            }
                break;
            case "Trizeps":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_trizeps);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_triceps, true));
                }
            }
                break;
            case "Beine":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_beine);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_legs, true));
                }
            }
                break;
            default:
                Log.e(TAG, "in setDataset: SOMETHING IS WRONG, can't find right group");
        }
    }
}
