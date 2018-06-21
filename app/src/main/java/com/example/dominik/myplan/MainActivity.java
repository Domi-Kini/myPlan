package com.example.dominik.myplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Trainingsplan> trainingsplaene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button addPlanButton = (Button) findViewById(R.id.button_add_plan);
        addPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddPlanIntent = new Intent(v.getContext(), AddPlanActivity.class);
                startActivity(openAddPlanIntent);

            }
        });
    }
}
