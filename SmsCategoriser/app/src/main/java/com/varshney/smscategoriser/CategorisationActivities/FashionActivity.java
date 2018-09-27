package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.varshney.smscategoriser.Adapter.FashionAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class FashionActivity extends AppCompatActivity {

    public static final String TAG = "FashionA";
    RecyclerView rvListFashion;
    FashionAdapter fashionAdapter;
    ArrayList<Message> fashionArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion);

        fashionArrayList = new ArrayList<>();
        Intent i =getIntent();
        fashionArrayList = (ArrayList<Message>) i.getSerializableExtra("list");
        rvListFashion = (RecyclerView) findViewById(R.id.rvListFashion);
        fashionAdapter = new FashionAdapter(this, fashionArrayList);
        rvListFashion.setLayoutManager(new LinearLayoutManager(this));
        rvListFashion.setAdapter(fashionAdapter);

    }
}
