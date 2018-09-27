package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.varshney.smscategoriser.Adapter.FinanceAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class FinanceActivity extends AppCompatActivity {

    public static final String TAG = "FoodA";
    RecyclerView rvListfinance;
    FinanceAdapter financeAdapter;

    ArrayList<Message> financeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        Intent i =getIntent();
        financeArrayList = (ArrayList<Message>) i.getSerializableExtra("list");
        rvListfinance = (RecyclerView) findViewById(R.id.rvListFinance);
        rvListfinance.setLayoutManager(new LinearLayoutManager(this));
        financeAdapter = new FinanceAdapter(this,financeArrayList);
        rvListfinance.setAdapter(financeAdapter);

    }
}
