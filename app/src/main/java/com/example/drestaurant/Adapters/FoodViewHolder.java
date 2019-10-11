package com.example.drestaurant.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drestaurant.Interfaces.ItemClickListener;
import com.example.drestaurant.R;



public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTitleView, mDespView,mPriceView;
    public ImageView mImageView;
    public ItemClickListener listener;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        mTitleView = (TextView) itemView.findViewById(R.id.item_title);
        mDespView = (TextView) itemView.findViewById(R.id.item_desp);
        mPriceView = (TextView) itemView.findViewById(R.id.item_price);
        mImageView = (ImageView) itemView.findViewById(R.id.item_image);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
            listener.onClick(v,getAdapterPosition(),false);
    }
}
