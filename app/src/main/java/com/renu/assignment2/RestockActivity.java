package com.renu.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {
    ArrayList<ProductItem> productItemList = new ArrayList<>();
    EditText quantity;
    Button ok_btu, cancel_btu;
    ListView updateqntlist;
    int index = -1;
    RestockAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        quantity = (EditText) findViewById(R.id.quantity);
        ok_btu = (Button) findViewById(R.id.ok_btu);
        cancel_btu = (Button) findViewById(R.id.cancel_btu);
        updateqntlist = (ListView) findViewById(R.id.updateqntlist);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        productItemList = (ArrayList<ProductItem>) data.getSerializable("productItmes");
        // Log.d(String.valueOf(RestockActivity.this),"historysize =" + productItemList.size());

        adapter = new RestockAdapter(getApplicationContext(), productItemList);
        updateqntlist.setAdapter( adapter);


        ok_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index != -1 && quantity != null && quantity.length() >0){

                    int updatedQnt =   productItemList.get(index).getProductQnt() + Integer.parseInt(quantity.getText().toString());
                    productItemList.get(index).setProductQnt(updatedQnt);
                    ( adapter).notifyDataSetChanged();
                }
                else {
                    Toast.makeText(RestockActivity.this,"Please fill up required Fields", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancel_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                Bundle data = new Bundle();
                data.putSerializable("newToDo",productItemList);
                returnIntent.putExtra("data", data);
                setResult(GlobalMethod.RESULT_RETURN_TO_MANAGER,returnIntent);
                finish();

            }
        });

        updateqntlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });



    }
}