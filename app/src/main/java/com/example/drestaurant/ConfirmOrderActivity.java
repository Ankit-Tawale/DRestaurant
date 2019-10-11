package com.example.drestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button confirmBtn;

    private String totalAmount = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this,"Total price = " + totalAmount,Toast.LENGTH_SHORT).show();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> tables = new ArrayList<String>();
        tables.add("Table 1");
        tables.add("Table 2");
        tables.add("Table 3");
        tables.add("Table 4");
        tables.add("Table 5");
        tables.add("Table 6");
        tables.add("Table 7");
        tables.add("Table 8");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tables);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        confirmBtn = (Button) findViewById(R.id.confirm_order_btn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
    }

    private void confirmOrder() {

        Toast.makeText(this,"Order Successfully placed.",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ConfirmOrderActivity.this,DashBoardActivity.class));
        finish();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
