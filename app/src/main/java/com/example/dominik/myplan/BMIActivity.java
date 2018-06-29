package com.example.dominik.myplan;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {
    private static final int MANN = 0;
    private static final int FRAU = 1;

    private int geschlecht = MANN;
    private double gewicht_wert;
    private double groesse_wert;

    private DecimalFormat d = new DecimalFormat("#.#");

    private EditText gewicht;
    private EditText groesse;
    private TextView bmi_wert;
    private TextView bmi;
    private TextView idealgewicht_wert;
    private TextView idealgewicht;
    private TextView spruch;
    private ColorStateList standardColors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Switch mSwitch = (Switch) findViewById(R.id.switch_geschlecht);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    geschlecht = FRAU;
                } else {
                    geschlecht = MANN;
                }
                updateViews();
            }
        });

        gewicht = (EditText) findViewById(R.id.editText_gewicht);
        gewicht.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = gewicht.getText().toString();
                if (!input.isEmpty())
                    gewicht_wert = Double.parseDouble(input);
                else
                    gewicht_wert = 0;
                Log.d("gewicht Wert: ", "" + d.format(gewicht_wert));
                updateViews();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        groesse = (EditText) findViewById(R.id.editText_groesse);
        groesse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = groesse.getText().toString();
                if (!input.isEmpty())
                    groesse_wert = Double.parseDouble(input);
                else
                    groesse_wert = 0;
                updateViews();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bmi = (TextView) findViewById(R.id.text_bmi);
        idealgewicht = (TextView) findViewById(R.id.text_idealgewicht);
        bmi_wert = (TextView) findViewById(R.id.text_bmi_wert);
        idealgewicht_wert = (TextView) findViewById(R.id.text_idealgewicht_wert);
        spruch = (TextView) findViewById(R.id.text_spruch);
        standardColors = bmi.getTextColors();
    }

    private void updateViews() {
        if (gewicht_wert == 0 && groesse_wert != 0) {
            double ideal;
            if (geschlecht == MANN)
                ideal = 22.6 * Math.pow(groesse_wert / 100, 2);
            else
                ideal = 18.5 * Math.pow(groesse_wert / 100, 2);

            idealgewicht.setTextColor(ContextCompat.getColor(this, R.color.green));
            idealgewicht_wert.setTextColor(ContextCompat.getColor(this, R.color.green));
            idealgewicht_wert.setText(d.format(ideal).toString());
            bmi.setTextColor(standardColors);
            bmi_wert.setTextColor(standardColors);
            bmi_wert.setText("?");
            spruch.setTextColor(standardColors);
            spruch.setText("Geben Sie Daten ein!");
        } else if (gewicht_wert != 0 && groesse_wert != 0) {
            double bmi_num = gewicht_wert / (Math.pow(groesse_wert / 100, 2));
            double ideal;
            if (geschlecht == MANN)
                ideal = 22.6 * Math.pow(groesse_wert / 100, 2);
            else
                ideal = 18.5 * Math.pow(groesse_wert / 100, 2);

            if (bmi_num < 16.0 || (bmi_num > 35.0 && bmi_num <= 40.0)) {
                make_color("red", bmi_num);
            } else if ((bmi_num >= 16.0 && bmi_num < 17.0) || (bmi_num > 30.0 && bmi_num <= 35.0)) {
                make_color("deep orange", bmi_num);
            } else if ((bmi_num >= 17.0 && bmi_num < 18.5) || (bmi_num > 25.0 && bmi_num <= 30.0)) {
                make_color("orange", bmi_num);
            } else if (bmi_num >= 18.5 && bmi_num <= 25.0) {
                make_color("green", bmi_num);
            } else if (bmi_num > 40.0) {
                make_color("deep red", bmi_num);
            }

            idealgewicht.setTextColor(ContextCompat.getColor(this, R.color.green));
            idealgewicht_wert.setTextColor(ContextCompat.getColor(this, R.color.green));
            idealgewicht_wert.setText(d.format(ideal).toString());

            bmi_wert.setText(d.format(bmi_num).toString());
        } else {
            bmi.setTextColor(standardColors);
            bmi_wert.setTextColor(standardColors);
            bmi_wert.setText("?");
            idealgewicht.setTextColor(standardColors);
            idealgewicht_wert.setTextColor(standardColors);
            idealgewicht_wert.setText("?");
            spruch.setTextColor(standardColors);
            spruch.setText("Geben Sie Daten ein!");
        }
    }

    private void make_color(String color, double bmi_num) {
        int color_code;
        switch(color) {
            case "deep red":
                color_code = ContextCompat.getColor(this, R.color.red_deep);
                break;
            case "red":
                color_code = ContextCompat.getColor(this, R.color.red);
                break;
            case "deep orange":
                color_code = ContextCompat.getColor(this, R.color.orange_deep);
                break;
            case "orange":
                color_code = ContextCompat.getColor(this, R.color.orange);
                break;
            case "green":
                color_code = ContextCompat.getColor(this, R.color.green);
                break;
            default:
                color_code = ContextCompat.getColor(this, R.color.green);
        }
        bmi_wert.setTextColor(color_code);
        this.bmi.setTextColor(color_code);
        spruch.setTextColor(color_code);

        if(bmi_num > 25.0)
            spruch.setText("Zeit für einen Lauf!");
        else if (bmi_num < 18.5)
            spruch.setText("Zeit zu Essen!");
        else
            spruch.setText("Gut in Form!");
    }
}
