package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.varshney.smscategoriser.Adapter.CabAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class CabActivity extends AppCompatActivity {

    public static final String TAG = "CabA";
    RecyclerView rvListCab;
    CabAdapter cabAdapter;
    ArrayList<Message> cabMsgArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab);

        cabMsgArrayList = new ArrayList<>();
        Intent i = getIntent();
        cabMsgArrayList = (ArrayList<Message>)i.getSerializableExtra("list");
        rvListCab = (RecyclerView) findViewById(R.id.rvListCab);
        rvListCab.setLayoutManager(new LinearLayoutManager(this));

        Log.d(TAG, "onCreate: Size Print"+cabMsgArrayList.size());
        cabAdapter= new CabAdapter(this,cabMsgArrayList);
        rvListCab.setAdapter(cabAdapter);
    }
}
