package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.varshney.smscategoriser.Adapter.FoodAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    public static final String TAG = "FoodA";
    RecyclerView rvListFood;
    FoodAdapter foodAdapter;

    ArrayList<Message> foodArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        foodArrayList = new ArrayList<>();
        Intent i = getIntent();
        foodArrayList = (ArrayList<Message>)i.getSerializableExtra("list");
        rvListFood = (RecyclerView) findViewById(R.id.rvListFood);
        rvListFood.setLayoutManager(new LinearLayoutManager(this));

       // Log.d(TAG, "onCreate: Size Print"+foodArrayList.size());
        foodAdapter= new FoodAdapter(this,foodArrayList);
        rvListFood.setAdapter(foodAdapter);
    }


}
