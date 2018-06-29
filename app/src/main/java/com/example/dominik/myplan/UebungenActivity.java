package com.example.dominik.myplan;

import android.content.Intent;
import android.content.res.TypedArray;
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

    public static final int SAFE = 1;
    public static final int CANCEL = 2;

    private ArrayList<ItemData> mDataset;
    private String mTitle;
    MySingleton singleton = MySingleton.getInstance();
    private int group;

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
        if (getIntent().hasExtra("musclegroup")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            group = getIntent().getIntExtra("musclegroup", -1);

            mTitle = getTitle(group);
        }
    }

    private String getTitle(int pos) {
        String[] titles = getResources().getStringArray(R.array.muskelgruppen);
        return titles[pos];
    }

    private void setDataset() {
        mDataset = new ArrayList<>();
        String[] title;
        TypedArray images;
        switch(mTitle) {
            case "Brust":
                title = getResources().getStringArray(R.array.uebungen_brust);
                images = getResources().obtainTypedArray(R.array.images_brust);
                break;
            case "Rücken":
                title = getResources().getStringArray(R.array.uebungen_rücken);
                images = getResources().obtainTypedArray(R.array.images_ruecken);
                break;
            case "Schultern":
                title = getResources().getStringArray(R.array.uebungen_schultern);
                images = getResources().obtainTypedArray(R.array.images_schultern);
                break;
            case "Bauch":
                title = getResources().getStringArray(R.array.uebungen_bauch);
                images = getResources().obtainTypedArray(R.array.images_bauch);
                break;
            case "Bizeps":
                title = getResources().getStringArray(R.array.uebungen_bizeps);
                images = getResources().obtainTypedArray(R.array.images_bizeps);
                break;
            case "Trizeps":
                title = getResources().getStringArray(R.array.uebungen_trizeps);
                images = getResources().obtainTypedArray(R.array.images_trizeps);
                break;
            case "Beine":
                title = getResources().getStringArray(R.array.uebungen_beine);
                images = getResources().obtainTypedArray(R.array.images_beine);
                break;
            default:
                Log.e(TAG, "in setDataset: SOMETHING IS WRONG, can't find right group");
                title = new String[1];
                title[0] = "Error";
                images = getResources().obtainTypedArray(R.array.images_group);
        }
        for (int i = 0; i < title.length; i++) {
            mDataset.add(new ItemData(title[i], images.getResourceId(i, -1), ItemData.UEBUNG, group));
        }
        images.recycle();
    }
}
