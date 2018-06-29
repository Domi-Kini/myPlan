package com.example.dominik.myplan;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowUebungActivity extends AppCompatActivity {

    private static final String TAG = "ShowUebungActivity";

    private LinearLayout linearParent;
    private MySingleton singelton = MySingleton.getInstance();
    private String mTitle;
    private int position = -1;
    private int group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_uebung);

        getIncomingIntent();

        TextView textView = (TextView) findViewById(R.id.text_uebung_name);
        textView.setText(mTitle);

        ImageView Image = (ImageView) findViewById(R.id.image_uebung);
        Image.setImageResource(singelton.getRightPlan().getUebung(position).getImgUrl());

        initLayout();
    }

    private void initLayout() {
        linearParent = (LinearLayout) findViewById(R.id.linearLayout_parent);
        View scrollViewChildElem;
        int wert = 0;
        for (int i = 0; i < singelton.getRightPlan().getUebung(position).getSaetze(); i++) {
            scrollViewChildElem = LayoutInflater.from(this).inflate(R.layout.layout_show_satz_element, linearParent,false);
            ((TextView) scrollViewChildElem.findViewById(R.id.text_satz)).setText("Satz " + (i + 1) + ":");
            wert = singelton.getRightPlan().getUebung(position).getGewicht(i);
            Log.d(TAG, "Gewicht: " + wert);
            ((TextView) scrollViewChildElem.findViewById(R.id.text_gewicht)).setText(wert + " kg");
            wert = singelton.getRightPlan().getUebung(position).getWH(i);
            Log.d(TAG, "Gewicht: " + wert);
            ((TextView) scrollViewChildElem.findViewById(R.id.text_wiederholung)).setText("" + wert);

            linearParent.addView(scrollViewChildElem);
        }
        Log.e(TAG, "initLayout() finished");
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("uebung_name")) {
            Log.d(TAG, "getIncomingIntent: found intent uebung_name");

            mTitle = getIntent().getStringExtra("uebung_name");
        }
        if (getIntent().hasExtra("group")) {
            group = getIntent().getIntExtra("group", -1);
        }
        if (getIntent().hasExtra("position")) {

            position = getIntent().getIntExtra("position", 0);

            Log.d(TAG, "getIncomingIntent: found intent position: " + position);
        }
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
