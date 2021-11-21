package com.renu.assignment2;

import static com.renu.assignment2.GlobalMethod.RESULT_RETURN_TO_MANAGER;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ManagerActivity extends Activity implements View.OnClickListener {
    Button history_btu;
    Button restock_btu;
    Button back_btu;

    String TAG = "ManagerActivity";
    ArrayList<ProductItem> historyList = new ArrayList<>();
    ArrayList<ProductItem> productItems = new ArrayList<>();
    ActivityResultLauncher<Intent> newToDoActivityResultLauncher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        history_btu = (Button) findViewById(R.id.history_btu);
        restock_btu = (Button) findViewById(R.id.restock_btu);
        back_btu = (Button) findViewById(R.id.back_btu);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        //this is for passed data to historyactivity
        historyList = (ArrayList<ProductItem>) data.getSerializable("HistoryList");
        //this is for passed data to restock activity
        productItems = (ArrayList<ProductItem>) data.getSerializable("Restock");
        // Log.d(TAG,"historysize =" + historyList.size());
        // Log.d(TAG,"Product Item List size" + productItems.size());

        history_btu.setOnClickListener(this);
        restock_btu.setOnClickListener(this);
        back_btu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_btu:
                Intent  intent = new Intent(this,HistoryActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("HistoryList",historyList);
                intent.putExtra("data", data);
                startActivity(intent);
                break;
            case R.id.restock_btu:
                intent = new Intent(this, RestockActivity.class);
                data = new Bundle();
                data.putSerializable("productItmes",productItems);
                intent.putExtra("data", data);
                startActivityForResult(intent,GlobalMethod.REQUEST_RETURN_TO_MANAGER);
                break;
            case R.id.back_btu:
                Intent intent1=getIntent();
                Bundle data1 = new Bundle();
                data1.putSerializable("newToDo",productItems);
                intent1.putExtra("data", data1);
                setResult(RESULT_OK,intent1);
                finish();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GlobalMethod.REQUEST_RETURN_TO_MANAGER && resultCode==RESULT_RETURN_TO_MANAGER){
            productItems=new ArrayList<>();
            Bundle bdata=data.getBundleExtra("data");
            productItems=bdata.getParcelableArrayList("newToDo");
        }
    }
}
