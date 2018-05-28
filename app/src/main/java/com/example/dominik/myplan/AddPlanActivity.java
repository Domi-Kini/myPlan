package com.example.dominik.myplan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPlanActivity extends AppCompatActivity {
    private String TrainingsplanName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_plan);
        final EditText PlanName = (EditText) findViewById(R.id.editText_nameofplan);

        final Button createPlanButton = findViewById(R.id.button_create_plan);
        createPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainingsplanName = PlanName.getText().toString();
                if (TrainingsplanName.equals("")) {
                    Toast toast = Toast.makeText(v.getContext(), "Bitte gib deinem Trainingsplan einen Namen!", Toast.LENGTH_LONG);
                    TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
                    tview.setTextColor(Color.RED);
                    tview.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    Intent openAddUebungIntent = new Intent(v.getContext(), AddUebungenActivity.class);
                    startActivity(openAddUebungIntent);
                }
            }
        });
    }
}
