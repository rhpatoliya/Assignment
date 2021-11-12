package com.renu.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final String TAG = "MainActivity";
ListView listItem;
TextView product_name_display, total_display,quantity_display;
TextView product_name,product_qnt,product_price ;
Button one_btu,two_btu,three_btu,four_btu,five_btu,six_btu,seven_btu,eight_btu,nine_btu,zero_btu;
Button buy_btu,clear_btu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<ProductItem> productItemList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"On create : Started");

        one_btu = (Button)findViewById(R.id.one_btu);
        two_btu = (Button)findViewById(R.id.two_btu);
        three_btu = (Button)findViewById(R.id.three_btu);
        four_btu = (Button)findViewById(R.id.four_btu);
        five_btu = (Button)findViewById(R.id.five_btu);
        six_btu = (Button)findViewById(R.id.six_btu);
        seven_btu = (Button)findViewById(R.id.seven_btu);
        eight_btu = (Button)findViewById(R.id.eight_btu);
        nine_btu = (Button)findViewById(R.id.nine_btu);
        zero_btu = (Button)findViewById(R.id.zero_btu);


        buy_btu = (Button)findViewById(R.id.buy_btu);
        clear_btu = (Button)findViewById(R.id.clear_btu);



        listItem = (ListView) findViewById(R.id.listItem);

        total_display = (TextView) findViewById(R.id.total_display);
        quantity_display = (TextView) findViewById(R.id.quantity_display);
        product_name_display = (TextView) findViewById(R.id.product_name_display);



        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Product Name will display", Toast.LENGTH_LONG).show();
                product_name_display.setText(productItemList.get(position).getProductItemName());

            }
        });









        ProductItem pants = new ProductItem("Pants",20.44, 10);
        ProductItem shoes = new ProductItem("Shoes", 10.44,100);
        ProductItem hats = new ProductItem("Hats", 5.9,30);

        //ArrayList<ProductItem> productItemList = new ArrayList<>();
        productItemList.add(pants);
        productItemList.add(shoes);
        productItemList.add(hats);

        ProductItemListAdapter adapter = new ProductItemListAdapter(this, R.layout.list_veiw, productItemList);
        listItem.setAdapter(adapter);

    }
    public void buttonclicked(View view){
    }




}