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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddPlanActivity extends AppCompatActivity {
    private String TrainingsplanName;
    private MySingleton singleton = MySingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_plan);

        final EditText PlanName = (EditText) findViewById(R.id.editText_nameofplan);

        Button addUebungenButton = (Button) findViewById(R.id.button_add_uebungen);
        addUebungenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainingsplanName = PlanName.getText().toString();
                if (TrainingsplanName.isEmpty()) {
                    Toast toast = Toast.makeText(v.getContext(), "Bitte gib deinem Trainingsplan einen Namen!", Toast.LENGTH_LONG);
                    TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
                    tview.setTextColor(Color.RED);
                    tview.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    Trainingsplan plan = new Trainingsplan(TrainingsplanName);
                    RadioGroup tag = (RadioGroup) findViewById(R.id.radioGroup_days);
                    if (tag.getCheckedRadioButtonId() != -1) {
                        RadioButton button = (RadioButton) findViewById(tag.getCheckedRadioButtonId());
                        plan.setTag(button.getText().toString());
                        Log.e("JSON", Integer.toString(tag.getCheckedRadioButtonId()));
                    }
                    singleton.addPlan(plan);
                    singleton.setIndex(singleton.getPlans().size() - 1);
                    Intent openAddUebungIntent = new Intent(v.getContext(), MuscleGroupActivity.class);
                    startActivity(openAddUebungIntent);
                    finish();
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.button_cancel_plan);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
