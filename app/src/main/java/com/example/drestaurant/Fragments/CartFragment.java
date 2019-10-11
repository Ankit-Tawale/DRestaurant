package com.example.drestaurant.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drestaurant.Adapters.CartViewHolder;
import com.example.drestaurant.ConfirmOrderActivity;
import com.example.drestaurant.DashBoardActivity;
import com.example.drestaurant.FoodDetailActivity;
import com.example.drestaurant.Models.Cart;
import com.example.drestaurant.Models.FoodModel;
import com.example.drestaurant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button nextButton;
    private TextView txtTotalAmount;
    private int overTotalPrice = 0;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = root.findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        nextButton = (Button) root.findViewById(R.id.next_button);
        txtTotalAmount = (TextView) root.findViewById(R.id.total_price);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTotalAmount.setText("Total Price = " + String.valueOf(overTotalPrice));

                Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                intent.putExtra("Total Price",String.valueOf(overTotalPrice));
                startActivity(intent);

            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        final DatabaseReference CartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(CartListRef.child("User View")
                .child("Food Data"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull final Cart cart) {

                        cartViewHolder.txtFoodTitle.setText(cart.getTitle());
                        cartViewHolder.txtFoodQuantity.setText(cart.getQuantity());
                        cartViewHolder.txtCartPrice.setText(cart.getPrice()+" Rs.");

                        int oneTypeFoodPrice = ((Integer.valueOf(cart.getPrice()))) * Integer.valueOf(cart.getQuantity());
                        overTotalPrice = overTotalPrice + oneTypeFoodPrice;

                        cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CharSequence options[] = new CharSequence[]{

                                        "Edit",
                                        "Remove"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Cart Options : ");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if(i == 0){
                                            Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
                                            intent.putExtra("fid", cart.getFid());
                                            startActivity(intent);
                                        }
                                        if(i == 1){
                                            CartListRef.child("User View")
                                                    .child("Food Data")
                                                    .child(cart.getFid())
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){
                                                                    Toast.makeText(getActivity(),"Item removed Succesfully",Toast.LENGTH_LONG).show();

                                                                    Intent intent1 = new Intent(getActivity(), DashBoardActivity.class);
                                                                    startActivity(intent1);

                                                                }
                                                        }
                                                    });
                                        }
                                    }
                                });

                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent,false);
                        CartViewHolder holder = new CartViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
