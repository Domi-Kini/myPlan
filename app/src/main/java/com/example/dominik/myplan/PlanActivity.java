package com.example.dominik.myplan;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {
    private static final String TAG = "PlanActivity";

    private MySingleton singleton = MySingleton.getInstance();
    //private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        TextView textName = (TextView) findViewById(R.id.text_plan_name);
        textName.setText(singleton.getRightPlan().getName());

        initLayout();
    }

    private ArrayList<ItemData> getDataset() {
        ArrayList<Uebung> uebungen = singleton.getRightPlan().getUebungen();
        ArrayList<ItemData> mDataset = new ArrayList<>();
        for (int i = 0; i < uebungen.size(); i++) {
            mDataset.add(new ItemData(uebungen.get(i).getName(), uebungen.get(i).getIconUrl(), (ItemData.UEBUNG | ItemData.PLAN), true));
        }
        return mDataset;
    }

    private void initLayout() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview_uebungen_list);
        View scrollViewChildElem = LayoutInflater.from(this).inflate(R.layout.layout_plan_element, scrollView, false);

        RecyclerView recyclerView = (RecyclerView) scrollViewChildElem.findViewById(R.id.recyclerview_plan);
        MyAdapter adapter = new MyAdapter(this, getDataset());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addUebungenButton = (Button) scrollViewChildElem.findViewById(R.id.button_add_uebungen);
        addUebungenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddUebungIntent = new Intent(v.getContext(), MuscleGroupActivity.class);
                startActivity(openAddUebungIntent);
                finish();
            }
        });
        scrollView.addView(scrollViewChildElem);
    }
}
