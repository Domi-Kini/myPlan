package com.example.dominik.myplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ConfigureUebungActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configure_uebung);

        ImageView Image = (ImageView) findViewById(R.id.image_uebung);
        Image.setImageResource(R.drawable.mucles_biceps);
    }
}
