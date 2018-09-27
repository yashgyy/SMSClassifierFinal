package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.varshney.smscategoriser.Adapter.HealthAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {

    public static final String TAG = "FoodA";
    RecyclerView rvListHealth;
    HealthAdapter healthAdapter;
    ArrayList<Message> healthMsgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Intent i = getIntent();
        healthMsgList = (ArrayList<Message>) i.getSerializableExtra("list");
        healthAdapter = new HealthAdapter(this,healthMsgList);
        rvListHealth = (RecyclerView) findViewById(R.id.rvListHealth);
        rvListHealth.setLayoutManager(new LinearLayoutManager(this));
        rvListHealth.setAdapter(healthAdapter);
    }

}
