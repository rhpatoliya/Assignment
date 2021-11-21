package com.renu.assignment2;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
Context mcontex;
ArrayList<ProductItem> historyItems = new ArrayList<>();

LayoutInflater layoutInflater;
    public HistoryAdapter(Context historyActivity, ArrayList<ProductItem> historyList) {
        this.mcontex = historyActivity;
        this.historyItems = historyList;
        this.layoutInflater = LayoutInflater.from(historyActivity);
    }

    @NonNull
    @Override
    public HistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_veiw,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.viewHolder holder, int position) {
        ProductItem productItem = historyItems.get(position);
        holder.product_name.setText(productItem.getProductItemName());
        holder.product_price.setText(productItem.getProductPrice() + "");
        double total = productItem.getProductQnt()  * productItem.getProductPrice();
        holder.product_total.setText(total + " ");

    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView product_name, product_price, product_total;
        private int position;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            product_name =  itemView.findViewById(R.id.product_name);
            product_price =  itemView.findViewById(R.id.product_price);
            product_total =  itemView.findViewById(R.id.product_total);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            Intent  intent = new Intent(mcontex, HistoryListActivity.class);
            intent.putExtra("productName", historyItems.get(position).getProductItemName()); //you can name the keys whatever you like
            intent.putExtra("productPrice", historyItems.get(position).getProductPrice()); //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
            intent.putExtra("purchaseDate", historyItems.get((int) Double.parseDouble(String.valueOf(position))).getPurchaseDate());
            mcontex.startActivity(intent);
        }

    }
}
