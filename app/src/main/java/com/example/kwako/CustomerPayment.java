package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kwako.adapters.customerPaymentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerPayment extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CustomerPaymentItem> customerPaymentItems;
    customerPaymentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);

        //setting the values from the inflator to the recycler view
        recyclerView = findViewById(R.id.recyclerviewcustomerpayment);

        customerPaymentItems = new ArrayList<>();
        customerPaymentItems.add(new CustomerPaymentItem("Nyeri","Bedsitter","2","1200","2400", "Show Unit"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new customerPaymentAdapter(getApplicationContext(),customerPaymentItems));
    }
}