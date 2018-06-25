package com.example.dominik.myplan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
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

    private static final String TAG = "ConfigureUebungActivity";

    private ArrayList<EditText> gewichte;
    private ArrayList<EditText> wiederholungen;
    private LinearLayout linearParent;
    private MySingleton singelton = MySingleton.getInstance();
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configure_uebung);

        getIncomingIntent();

        TextView textView = (TextView) findViewById(R.id.text_uebung_name);
        textView.setText(mTitle);

        ImageView Image = (ImageView) findViewById(R.id.image_uebung);
        Image.setImageResource(R.drawable.uebung_schraegbankdruecken);

        initLayout();

        Button cancelButton = (Button) findViewById(R.id.button_cancel_hinzufügen);
        Button addSatzButton = (Button) findViewById(R.id.button_add_satz);
        Button addButton = (Button) findViewById(R.id.button_hinzufuegen);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                    makeToast(v, "Bitte gib gültige Werte ein.", true);
                } else {
                    ArrayList<Satz> saetze = getSaetze();
                    Satz[] satzarr = new Satz[saetze.size()];
                    satzarr = saetze.toArray(satzarr);
                    Uebung uebung = new Uebung(mTitle, satzarr);
                    singelton.getRightPlan().setUebung(uebung);

                    makeToast(v, "Übung gespeichert", false);
                    finish();
                }
            }
        });
    }

    private void initLayout() {
        linearParent = (LinearLayout) findViewById(R.id.linearLayout_parent);
        View scrollViewChildElem = LayoutInflater.from(this).inflate(R.layout.layout_satz_element, linearParent,false);

        gewichte = new ArrayList<>();
        gewichte.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_gewicht));
        wiederholungen = new ArrayList<>();
        wiederholungen.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_wiederholung));

        linearParent.addView(scrollViewChildElem);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("uebung_name")) {
            Log.d(TAG, "getIncomingIntent: found intent uebung_name");

            mTitle = getIntent().getStringExtra("uebung_name");
        }
    }

    private void newSatz() {
        View scrollViewChildElem = LayoutInflater.from(ConfigureUebungActivity.this).inflate(R.layout.layout_satz_element, linearParent, false);

        gewichte.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_gewicht));
        wiederholungen.add((EditText) scrollViewChildElem.findViewById(R.id.edittext_wiederholung));

        linearParent.addView(scrollViewChildElem);
    }

    private boolean isvalid() {
        boolean isvalid = false;
        for (int i = 0; i < gewichte.size(); i++) {
            if (gewichte.get(i).getText().toString().isEmpty() && !wiederholungen.get(i).getText().toString().isEmpty())
                isvalid = false;
            else if (!gewichte.get(i).getText().toString().isEmpty() && wiederholungen.get(i).getText().toString().isEmpty())
                isvalid = false;
            else if (gewichte.get(i).getText().toString().equals("0") || wiederholungen.get(i).getText().toString().equals("0"))
                isvalid = false;
            else if (!gewichte.get(i).getText().toString().isEmpty() && !wiederholungen.get(i).getText().toString().isEmpty())
                isvalid = true;
        }
        return isvalid;
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

    private void makeToast(View v, String message, boolean isError) {

        Toast toast = Toast.makeText(v.getContext(), message, Toast.LENGTH_LONG);
        TextView tview = (TextView) toast.getView().findViewById(android.R.id.message);
        if (isError)
            tview.setTextColor(Color.RED);
        tview.setGravity(Gravity.CENTER);
        toast.show();
    }
}
