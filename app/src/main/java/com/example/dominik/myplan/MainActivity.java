package com.example.dominik.myplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MySingleton singleton = MySingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = readData();
        if (data != null && !data.isEmpty()) {
            Log.e("JSON data: ", data);
            Trainingsplan[] plan = new Gson().fromJson(data, Trainingsplan[].class);

            ArrayList<Trainingsplan> plaene =new ArrayList<Trainingsplan>(Arrays.asList(plan));
            singleton.setPlans(plaene);
        }

        initLayout();
    }

    private void initLayout() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview_main);
        View scrollViewChildElem = LayoutInflater.from(this).inflate(R.layout.layout_main, scrollView, false);

        mRecyclerView = (RecyclerView) scrollViewChildElem.findViewById(R.id.recyclerview_main);
        MyAdapter adapter = new MyAdapter(this, getDataset());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Button addPlanButton = (Button) scrollViewChildElem.findViewById(R.id.button_add_plan);
        addPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddPlanIntent = new Intent(v.getContext(), AddPlanActivity.class);
                startActivity(openAddPlanIntent);

            }
        });
        scrollView.addView(scrollViewChildElem);
    }

    private ArrayList<ItemData> getDataset() {
        ArrayList<ItemData> list = new ArrayList<>();
        ItemData item;
        int image;
        if (singleton.getPlans() != null) {
            if (!singleton.getPlans().isEmpty()) {
                for (int i = 0; i < singleton.getPlans().size(); i++) {
                    switch(singleton.getPlans().get(i).getTag()) {
                        case "Montag":
                            image = R.drawable.monday;
                            break;
                        case "Dienstag":
                            image = R.drawable.tuesday;
                            break;
                        case "Mittwoch":
                            image = R.drawable.wednesday;
                            break;
                        case "Donnerstag":
                            image = R.drawable.thursday;
                            break;
                        case "Freitag":
                            image = R.drawable.friday;
                            break;
                        case "Samstag":
                            image = R.drawable.saturday;
                            break;
                        case "Sonntag":
                            image = R.drawable.sunday;
                            break;
                        default:
                            image = R.drawable.no_day;
                    }
                    item = new ItemData(singleton.getPlans().get(i).getName(), image, ItemData.PLAN);
                    list.add(item);
                }
            }
        }
        return list;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<ItemData> mDataset = getDataset();
        MyAdapter adapter = new MyAdapter(this, mDataset);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String json = new Gson().toJson(singleton.getPlans());
        safeData(json);
    }

    private void safeData(String json_data) {
        String filename = "safed_data.json";
        try {
            FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
            if (json_data != null && !json_data.equals("null"))
                fileOutputStream.write(json_data.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readData() {
        try {
            String json_data;
            FileInputStream fileInputStream = openFileInput("safed_data.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            if (bufferedReader != null) {
                StringBuffer stringBuffer = new StringBuffer();
                while ((json_data = bufferedReader.readLine()) != null) {
                    stringBuffer.append(json_data + "\n");
                }
                return stringBuffer.toString();
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
