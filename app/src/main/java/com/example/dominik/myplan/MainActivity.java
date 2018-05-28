package com.example.dominik.myplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Trainingsplan> trainingsplaene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddPlan(View v) {
        Intent intent = new Intent(this, AddPlanActivity.class);
        startActivity(intent);
    }
}
