package com.example.drestaurant.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drestaurant.Adapters.FoodViewHolder;
import com.example.drestaurant.FoodDetailActivity;
import com.example.drestaurant.Models.FoodModel;
import com.example.drestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DatabaseReference mRef;
    View view;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    public PopularFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_popular, container, false);

        mRecyclerView = view.findViewById(R.id.rv_data);


        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);



        mRef = FirebaseDatabase.getInstance().getReference().child("Food Data");



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<FoodModel> options
                = new FirebaseRecyclerOptions.Builder<FoodModel>()
                .setQuery(mRef, FoodModel.class)
                .build();



        FirebaseRecyclerAdapter<FoodModel, FoodViewHolder> adapter =
                new FirebaseRecyclerAdapter<FoodModel, FoodViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i, @NonNull final FoodModel foodModel) {

                        foodViewHolder.mTitleView.setText(foodModel.getTitle());
                        foodViewHolder.mDespView.setText(foodModel.getDescription());
                        foodViewHolder.mPriceView.setText(foodModel.getPrice());
                        Picasso.get().load(foodModel.getImage()).into(foodViewHolder.mImageView);

                        foodViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
                                intent.putExtra("fid",foodModel.getFid());
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
                        FoodViewHolder holder = new FoodViewHolder(view);
                        return holder;
                    }
                };
        mRecyclerView.setAdapter(adapter);

        adapter.startListening();


    }

}
