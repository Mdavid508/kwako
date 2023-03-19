package com.example.kwako.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.CustomerPaymentItem;
import com.example.kwako.CustomerPaymentViewHolder;
import com.example.kwako.R;

import java.util.List;

public class customerPaymentAdapter extends RecyclerView.Adapter<CustomerPaymentViewHolder>{



    Context applicationContext;
    List<CustomerPaymentItem> customerPaymentItems;

    public customerPaymentAdapter(android.content.Context applicationContext, List<CustomerPaymentItem> customerPaymentItems) {
        this.customerPaymentItems = customerPaymentItems;
        this.applicationContext = applicationContext;
    }


    @NonNull
    @Override
    public CustomerPaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerPaymentViewHolder(LayoutInflater.from(applicationContext).inflate(R.layout.customer_payment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPaymentViewHolder holder, int position) {
        holder.tvLocation.setText(customerPaymentItems.get(position).getTvLocation());
        holder.tvHouseType.setText(customerPaymentItems.get(position).getTvHouseType());
        holder.tvUnitsNumber.setText(customerPaymentItems.get(position).getTvHouseType());
        holder.tvTotalAmount.setText(customerPaymentItems.get(position).getTvTotalAmount());
        holder.tvUnitPrice.setText(customerPaymentItems.get(position).getTvUnitPrice());
        holder.tvShowUnit.setText(customerPaymentItems.get(position).getTvShowUnit());
    }

    @Override
    public int getItemCount() {
        return customerPaymentItems.size();
    }
}