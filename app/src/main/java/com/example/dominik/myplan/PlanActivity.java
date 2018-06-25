package com.example.dominik.myplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {
    private static final String TAG = "PlanActivity";

    private MySingleton singleton = MySingleton.getInstance();
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        TextView textName = (TextView) findViewById(R.id.text_plan_name);
        textName.setText(singleton.getRightPlan().getName());

        ArrayList<ItemData> mDataset = setDataset();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_plan);
        MyAdapter adapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<ItemData> setDataset() {
        ArrayList<Uebung> uebungen = singleton.getRightPlan().getUebungen();
        ArrayList<ItemData> mDataset = new ArrayList<>();
        for (int i = 0; i < uebungen.size(); i++) {
            mDataset.add(new ItemData(uebungen.get(i).getName(), R.drawable.plan_icon_placeholder, ItemData.UEBUNG));
        }
        return mDataset;
    }
}
