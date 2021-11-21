package com.renu.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RestockAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    // private int mResource;
    ArrayList<ProductItem> myList;

    public RestockAdapter (Context context, ArrayList<ProductItem> myList) {
        this.context = context;
        this.myList = myList;
        inflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.restock_list,null);

        TextView product_name =  convertView.findViewById(R.id.product_name);
        TextView product_price = convertView.findViewById(R.id.product_price);
        TextView product_qnt = convertView.findViewById(R.id.product_qnt);

        product_name.setText(myList.get(position).getProductItemName());
        product_price.setText(String.valueOf(myList.get(position).getProductPrice()));
        product_qnt.setText(String.valueOf(myList.get(position).getProductQnt()));
        System.out.println(myList);
        return convertView;
    }
}
