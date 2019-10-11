package com.example.drestaurant.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drestaurant.Interfaces.ItemClickListener;
import com.example.drestaurant.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtFoodTitle,txtFoodQuantity,txtCartPrice;
    ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtFoodTitle = itemView.findViewById(R.id.cart_title);
        txtCartPrice = itemView.findViewById(R.id.cart_price);
        txtFoodQuantity = itemView.findViewById(R.id.cart_quantity);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
