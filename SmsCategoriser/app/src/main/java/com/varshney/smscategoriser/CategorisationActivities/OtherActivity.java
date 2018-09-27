package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.varshney.smscategoriser.Adapter.OtherAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity {

    public static final String TAG = "OtherA";
    RecyclerView rvListOther;
    OtherAdapter otherAdapter;

    ArrayList<Message> otherArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        otherArrayList = new ArrayList<>();
        Intent i = getIntent();
        otherArrayList = (ArrayList<Message>)i.getSerializableExtra("list");
        rvListOther = (RecyclerView) findViewById(R.id.rvListOther);
        rvListOther.setLayoutManager(new LinearLayoutManager(this));
        otherAdapter= new OtherAdapter(this,otherArrayList);
        rvListOther.setAdapter(otherAdapter);
    }

    public void updateList(ArrayList<Message> msgList)
    {
        otherAdapter.updateOtherList(msgList);
    }
}
