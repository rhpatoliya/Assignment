package com.renu.assignment2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.parser.Position;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    Button manager_botton;
    ListView listItem;
    TextView product_name_display, total_display, quantity_display;
    TextView product_name;
    TextView product_qnt;
    TextView product_price;
    Button one_btu, two_btu, three_btu, four_btu, five_btu, six_btu, seven_btu, eight_btu, nine_btu, zero_btu;
    Button buy_btu, clear_btu;

    String text = "";
    Manager managerObj = new Manager();

    boolean isvalid;

    int selectePosition = -1;

    ProductItemListAdapter adapter;

    ActivityResultLauncher<Intent> newToDoActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  ArrayList<ProductItem> productItemList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "On create : Started");
        managerObj.addAll();
        one_btu = (Button) findViewById(R.id.one_btu);
        two_btu = (Button) findViewById(R.id.two_btu);
        three_btu = (Button) findViewById(R.id.three_btu);
        four_btu = (Button) findViewById(R.id.four_btu);
        five_btu = (Button) findViewById(R.id.five_btu);
        six_btu = (Button) findViewById(R.id.six_btu);
        seven_btu = (Button) findViewById(R.id.seven_btu);
        eight_btu = (Button) findViewById(R.id.eight_btu);
        nine_btu = (Button) findViewById(R.id.nine_btu);
        zero_btu = (Button) findViewById(R.id.zero_btu);

        manager_botton = (Button) findViewById(R.id.manager_botton);


        buy_btu = (Button) findViewById(R.id.buy_btu);
        clear_btu = (Button) findViewById(R.id.clear_btu);


        listItem = (ListView) findViewById(R.id.listItem);

        total_display = (TextView) findViewById(R.id.total_display);
        quantity_display = (TextView) findViewById(R.id.quantity_display);
        product_name_display = (TextView) findViewById(R.id.product_name_display);
        product_name = (TextView) findViewById(R.id.product_name);
        product_qnt = (TextView) findViewById(R.id.product_qnt);
        product_price = (TextView) findViewById(R.id.product_price);


        manager_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"Back to screen");
                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("HistoryList",managerObj.HistoryItemList);
                data.putSerializable("Restock", managerObj.productItemList);
                intent.putExtra("data", data);
                //startActivity(intent);
                newToDoActivityResultLauncher.launch(intent);
            }
        });


        adapter = new ProductItemListAdapter(getApplicationContext(), managerObj.productItemList);
        listItem.setAdapter(adapter);

        clear_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "";
                quantity_display.setText("");
                total_display.setText("");

            }
        });

        buy_btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectePosition != -1 && text != null && text.length() >0){

                    if(!isvalid){
                        int newListQuanity = managerObj.productItemList.get(selectePosition).getProductQnt() - Integer.parseInt(text);
                        managerObj.createHistory(selectePosition, text);

                        managerObj.productItemList.get(selectePosition).setProductQnt(newListQuanity);
                        adapter.notifyDataSetChanged();

                        //AltertDialog for users Thanks massage
                        AlertDialogMsg();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this,"Please fill up required Fields", Toast.LENGTH_LONG).show();
                }
                clearUI();

            }

        });

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Product Name will display", Toast.LENGTH_LONG).show();
                selectePosition = position;
                //display the name of product on display text view
                product_name_display.setText(managerObj.productItemList.get(position).getProductItemName());
                String selectedName = managerObj.productItemList.get(position).getProductItemName();
            }
        });

        newToDoActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Bundle bdata=data.getBundleExtra("data");
                            ArrayList<ProductItem> historyList=bdata.getParcelableArrayList("newToDo");
                            for(int i=0;i<historyList.size();i++){
                                managerObj.productItemList.set(i,historyList.get(i));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
        );



    }

    private void clearUI() {
        quantity_display.setText("");
        total_display.setText("");
    }

    private void AlertDialogMsg() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Thank You for your Purchage");
        alertDialog.setMessage("Your purchased is " + Integer.parseInt(text) + " "+ managerObj.productItemList.get(selectePosition).productItemName + " "+ "for" + " " +
                (Integer.parseInt(text) * (managerObj.productItemList.get(selectePosition).productPrice)));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void buttonclicked(View view) {
// for displaying quantity in quantity filed

        Button button = (Button) view;
        String buttontitle = button.getText().toString();
        text = text + buttontitle;
        quantity_display.setText(text);


        if(selectePosition != -1) {
            isvalid =  managerObj.checkquantity(selectePosition,Integer.parseInt(text));
            if (isvalid){
                Toast.makeText(MainActivity.this, "Its not enough Quantity", Toast.LENGTH_LONG).show();
            }
           /* else{
                int newListQuanity = managerObj.productItemList.get(selectePosition).getProductQnt() - Integer.parseInt(text);

            }*/
            double price = managerObj.productItemList.get(selectePosition).getProductPrice();
            int total = (int) (price * Integer.parseInt(text));
            total_display.setText(total + "");



        }

    }
}