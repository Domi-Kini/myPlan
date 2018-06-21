package com.example.dominik.myplan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConfigureUebungActivity extends AppCompatActivity {
    Button addSatzButton;
    Button addButton;
    ArrayList<EditText> gewichte;
    ArrayList<EditText> wiederholungen;
    LinearLayout linearParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configure_uebung);

        ImageView Image = (ImageView) findViewById(R.id.image_uebung);
        Image.setImageResource(R.drawable.uebung_schraegbankdruecken);

        addButton = (Button) findViewById(R.id.button_hinzufuegen);
        linearParent = (LinearLayout) findViewById(R.id.linearLayout_parent);
        View scrollViewChildElem = LayoutInflater.from(this).inflate(R.layout.layout_satz_element, linearParent,false);

        addSatzButton = (Button) scrollViewChildElem.findViewById(R.id.button_add_satz);
        addSatzButton.setVisibility(View.VISIBLE);
        gewichte = new ArrayList<>();
        gewichte.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_gewicht));
        wiederholungen = new ArrayList<>();
        wiederholungen.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_wiederholung));

        linearParent.addView(scrollViewChildElem);


        addSatzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSatz();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isvalid()) {
                    Toast toast = Toast.makeText(v.getContext(), "Bitte gib gültige Werte ein.", Toast.LENGTH_LONG);
                    TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
                    tview.setTextColor(Color.RED);
                    tview.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    ArrayList<Satz> saetze = getSaetze();
                    //DO STH!!!
                    finish();
                }
            }
        });
    }

    private void newSatz() {
        View scrollViewChildElem = LayoutInflater.from(ConfigureUebungActivity.this).inflate(R.layout.layout_satz_element, linearParent, false);

        gewichte.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_gewicht));
        wiederholungen.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_wiederholung));

        linearParent.addView(scrollViewChildElem);
    }

    private boolean isvalid() {
        if(gewichte.get(0).getText().toString().isEmpty() && wiederholungen.get(0).getText().toString().isEmpty() && gewichte.size() == 1)
            return false;
        for (int i = 0; i < gewichte.size(); i++) {
            if (gewichte.get(i).getText().toString().isEmpty() && !wiederholungen.get(i).getText().toString().isEmpty())
                return false;
            else if (!gewichte.get(i).getText().toString().isEmpty() && wiederholungen.get(i).getText().toString().isEmpty())
                return false;
            else if (gewichte.get(i).getText().toString().equals("0") || wiederholungen.get(i).getText().toString().equals("0"))
                return false;
        }
        return true;
    }

    private ArrayList<Satz> getSaetze() {
        ArrayList<Satz> saetze = new ArrayList<>();
        for(int i = 0; i < gewichte.size(); i++) {
            if (!gewichte.get(i).getText().toString().isEmpty()){
                saetze.add(new Satz(Integer.parseInt(gewichte.get(i).getText().toString()), Integer.parseInt(wiederholungen.get(i).getText().toString())));
            }
        }

        return saetze;
    }
}
