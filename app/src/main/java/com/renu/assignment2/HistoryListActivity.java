package com.renu.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity {
    TextView product_name;
    TextView product_price;
    TextView purchase_date;
    ArrayList<ProductItem> historyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        product_name = (TextView) findViewById(R.id.product_name);
        product_price = (TextView) findViewById(R.id.product_price);
        purchase_date  = (TextView) findViewById(R.id.purchase_date);

        String name = getIntent().getExtras().getString("productName");
        double price = getIntent().getExtras().getDouble("productPrice");
        String date = getIntent().getExtras().getString("purchaseDate");

        product_name.setText("productName: " + name);
        product_price.setText("productPrice: " + price);
        purchase_date.setText("PurchaseDate:" + date);

    }
}