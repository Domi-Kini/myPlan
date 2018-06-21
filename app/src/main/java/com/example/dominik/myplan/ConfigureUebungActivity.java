package com.example.dominik.myplan;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfigureUebungActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configure_uebung);

        ImageView Image = (ImageView) findViewById(R.id.image_uebung);
        Image.setImageResource(R.drawable.uebung_schraegbankdruecken);

        Button addSatzButton = (Button) findViewById(R.id.satz_button);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.configure_uebung_constraint_layout);
        addSatzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSatz();


            }
        });
    }

    private void newSatz() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.configure_uebung_constraint_layout);
        ConstraintSet constraintSet = new ConstraintSet();

        TextView gewicht = new TextView(ConfigureUebungActivity.this);
        gewicht.setText("GEWICHT");
        ConstraintLayout.LayoutParams clgewicht = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);


        constraintSet.clone(constraintLayout);
        constraintSet.applyTo(constraintLayout);
        constraintLayout.addView(gewicht, clgewicht);

    }
}
