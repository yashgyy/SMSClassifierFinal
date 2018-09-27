package com.varshney.smscategoriser.CategorisationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.varshney.smscategoriser.Adapter.TelecomAdapter;
import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

public class TelecomActivity extends AppCompatActivity {


    public static final String TAG = "telecomA";
    RecyclerView rvListTelecom;
    TelecomAdapter telecomAdapter;
    ArrayList<Message> telecomMsgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecom);

        Intent i = getIntent();
        telecomMsgList = (ArrayList<Message>) i.getSerializableExtra("list");
        rvListTelecom = (RecyclerView) findViewById(R.id.rvListTelecom);
        rvListTelecom.setLayoutManager(new LinearLayoutManager(this));
        telecomAdapter = new TelecomAdapter(this, telecomMsgList);
        rvListTelecom.setAdapter(telecomAdapter);
    }
}
