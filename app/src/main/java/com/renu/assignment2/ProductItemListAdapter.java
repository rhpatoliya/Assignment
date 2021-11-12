package com.renu.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Carousel;

import java.util.ArrayList;
import java.util.List;

public class ProductItemListAdapter extends ArrayAdapter<ProductItem> {
    private static final String TAG = "productItemListAdapter";
    private LayoutInflater inflater;
    private Context mContext;
    private int mResource;
    private  ArrayList<ProductItem> productItems;

    public ProductItemListAdapter(Context context, int resource, ArrayList<ProductItem> objects) {
        super(context,
                resource,
                objects);
        mContext = context;
        mResource = resource;
    }
//@param context
   // @param resource
   // @param objects
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String productItemName = getItem(position).getProductItemName();
        double productPrice = getItem(position).getProductPrice();
        int productQnt = (int) getItem(position).getProductQnt();

        ProductItem productItem = new ProductItem(productItemName,productPrice,productQnt);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView product_name = (TextView) convertView.findViewById(R.id.product_name);
        TextView product_price = (TextView) convertView.findViewById(R.id.product_price);
        TextView product_qnt = (TextView) convertView.findViewById(R.id.product_qnt);

        product_name.setText(productItemName);
        product_price.setText( productPrice + "");
        product_qnt.setText(productQnt+ "");

        return convertView;
    }
}
