package com.example.dominik.myplan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UebungenActivity extends AppCompatActivity {

    private static final String TAG = "UebungenActivity";

    public static final int SAFE = 0;
    public static final int CANCEL = 1;

    private ArrayList<ItemData> mDataset;
    private String mTitle;
    MySingleton singleton = MySingleton.getInstance();

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        getIncomingIntent();
        setDataset();

        TextView textTitle = (TextView) findViewById(R.id.text_uebung_title);
        textTitle.setText(mTitle);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        MyAdapter adapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button createPlanButton = (Button) findViewById(R.id.button_create_plan);
        createPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.button_cancel_plan);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void cancel() {
        setResult(CANCEL);
        finish();
    }

    private void create() {
        if (singleton.getRightPlan().getUebungen() != null) {
            setResult(SAFE);
            finish();
        } else {
            Toast toast = Toast.makeText(UebungenActivity.this, "Bitte füge zuerst eine Übung hinzu.", Toast.LENGTH_LONG);
            TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
            tview.setTextColor(Color.RED);
            tview.setGravity(Gravity.CENTER);
            toast.show();
        }
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
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_chest, ItemData.UEBUNG));
                    }
                }
                break;
            case "Ruecken":
                {
                    String[] title = getResources().getStringArray(R.array.uebungen_rücken);
                    for (int i = 0; i < title.length; i++) {
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_back, ItemData.UEBUNG));
                    }
                }
                break;
            case "Schultern":
                {
                    String[] title = getResources().getStringArray(R.array.uebungen_schultern);
                    for (int i = 0; i < title.length; i++) {
                        mDataset.add(new ItemData(title[i], R.drawable.mucles_shoulders, ItemData.UEBUNG));
                    }
                }
                break;
            case "Bauch":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_bauch);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_abdominals, ItemData.UEBUNG));
                }
            }
                break;
            case "Bizeps":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_bizeps);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_biceps, ItemData.UEBUNG));
                }
            }
                break;
            case "Trizeps":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_trizeps);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_triceps, ItemData.UEBUNG));
                }
            }
                break;
            case "Beine":
            {
                String[] title = getResources().getStringArray(R.array.uebungen_beine);
                for (int i = 0; i < title.length; i++) {
                    mDataset.add(new ItemData(title[i], R.drawable.mucles_legs, ItemData.UEBUNG));
                }
            }
                break;
            default:
                Log.e(TAG, "in setDataset: SOMETHING IS WRONG, can't find right group");
        }
    }
}
