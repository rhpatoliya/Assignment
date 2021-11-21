package com.renu.assignment2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends Activity {
   RecyclerView recyclerview;
   HistoryAdapter adapter;
    String TAG = "HistoryActivity";
    ArrayList<ProductItem> historyList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        historyList = (ArrayList<ProductItem>) data.getSerializable("HistoryList");
        Log.d(TAG,"historysize =" + historyList.size());

        adapter = new HistoryAdapter(this, historyList);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);


    }
}
