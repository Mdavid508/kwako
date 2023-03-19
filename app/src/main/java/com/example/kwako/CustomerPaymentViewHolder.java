package com.example.kwako;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerPaymentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvLocation;
    public TextView tvHouseType;
    public TextView tvUnitsNumber;
    public TextView tvUnitPrice;
    public TextView tvTotalAmount;
    public TextView tvShowUnit;
    public CustomerPaymentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvLocation = itemView.findViewById(R.id.tvLocation);
        tvHouseType = itemView.findViewById(R.id.tvHouseType);
        tvUnitsNumber = itemView.findViewById(R.id.tvUnitsNumber);
        tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
        tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
        tvShowUnit = itemView.findViewById(R.id.tvShowUnit);
    }
}
