package com.example.dominik.myplan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
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
    public static final int REQUEST_FINISH = 0;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private boolean newPlan = true;
    private int startIndex = 0;
    private MySingleton singleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_uebungen);

        if (singleton.getRightPlan().getUebungen() != null) {
            startIndex = singleton.getRightPlan().getUebungen().size();
        }

        TextView textTitle = (TextView) findViewById(R.id.text_uebung_title);
        textTitle.setText("Muskelgruppen");

        ArrayList<ItemData> mDataset = setDataset();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mAdapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(mAdapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FINISH) {
            switch(resultCode) {
                case UebungenActivity.CANCEL:
                    cancel();
                    break;
                case UebungenActivity.SAFE:
                    create();
                    break;
            }
        }
    }

    private void create() {
        if (singleton.getRightPlan().getUebungen() != null) {
            finish();
        } else {
            Toast toast = Toast.makeText(MuscleGroupActivity.this, "Bitte füge zuerst eine Übung hinzu.", Toast.LENGTH_LONG);
            TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
            tview.setTextColor(Color.RED);
            tview.setGravity(Gravity.CENTER);
            toast.show();
        }
    }

    private void cancel() {
        if (singleton.getRightPlan().getUebungen() != null) {
            for (int i = startIndex; i < singleton.getRightPlan().getUebungen().size(); i++) {
                singleton.getRightPlan().deleteUebung(i);
            }
            if (singleton.getRightPlan().getUebungen().isEmpty())
                singleton.getPlans().remove(singleton.getIndex());
        } else {
            singleton.getPlans().remove(singleton.getIndex());
        }
        finish();
    }

    private ArrayList<ItemData> setDataset() {
        ArrayList<ItemData> mDataset = new ArrayList<>();
        String[] TitleArray = getResources().getStringArray(R.array.muskelgruppen);
        TypedArray images = getResources().obtainTypedArray(R.array.images_group);
        for (int i = 0; i < TitleArray.length; i++) {
            mDataset.add(new ItemData(TitleArray[i], images.getResourceId(i, -1), ItemData.GROUP));
        }
        images.recycle();
        return mDataset;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            cancel();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        cancel();
        return;
    }
}
