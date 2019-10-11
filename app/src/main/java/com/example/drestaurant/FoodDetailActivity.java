package com.example.drestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.drestaurant.Models.FoodModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FoodDetailActivity extends AppCompatActivity {

    private Button addToCart;
    private ImageView imageView;
    private ElegantNumberButton numberButton;
    private String FoodId = "";
    private TextView foodTitle,foodDesp,foodPrice;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        FoodId = getIntent().getStringExtra("fid");

        addToCart = (Button) findViewById(R.id.food_add_to_cart);
        imageView = (ImageView) findViewById(R.id.food_image_details);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        foodTitle = (TextView) findViewById(R.id.food_title_details);
        foodDesp = (TextView) findViewById(R.id.food_description_details);
        foodPrice = (TextView) findViewById(R.id.food_price_details);
        toolbar = (Toolbar) findViewById(R.id.food_detail_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getFoodDetail(FoodId);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addingToCartList();
            }
        });
    }

    private void addingToCartList() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("fid", FoodId);
        cartMap.put("title", foodTitle.getText().toString());
        cartMap.put("description", foodDesp.getText().toString());
        cartMap.put("price", foodPrice.getText().toString());
        cartMap.put("quantity", numberButton.getNumber());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);

        cartListRef.child("User View").child("Food Data")
                .child(FoodId)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            cartListRef.child("Admin View").child("Food Data")
                                    .child(FoodId)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(FoodDetailActivity.this,"Added to Cart",Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(FoodDetailActivity.this, DashBoardActivity.class);
                                            startActivity(intent);

                                        }
                                    });

                        }
                    }
                });

    }

    private void getFoodDetail(String FoodId) {

        DatabaseReference foodRef = FirebaseDatabase.getInstance().getReference().child("Food Data");

        foodRef.child(FoodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        FoodModel foodModel = dataSnapshot.getValue(FoodModel.class);

                        foodTitle.setText(foodModel.getTitle());
                        foodDesp.setText(foodModel.getDescription());
                        foodPrice.setText(foodModel.getPrice());
                        Picasso.get().load(foodModel.getImage()).into(imageView);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
