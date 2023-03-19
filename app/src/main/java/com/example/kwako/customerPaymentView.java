package com.example.kwako;

import android.widget.TextView;

public class customerPaymentView {
//    TextView tvLocation;
//    TextView tvHouseType;
//    TextView tvUnitsNumber;
//    TextView tvUnitsPrice;
//    TextView tvTotalAmount;
//    TextView tvShowUnit;

    private String tvLocation;
    private String tvHouseType;
    private int tvUnitsNumber;
    private int tvUnitPrice;
    private int tvTotalAmount;
    private String tvShowUnit;

    //generate setters

    public void setTvLocation(String tvLocation) {
        this.tvLocation = tvLocation;
    }

    public void setTvHouseType(String tvHouseType) {
        this.tvHouseType = tvHouseType;
    }

    public void setTvUnitsNumber(int tvUnitsNumber) {
        this.tvUnitsNumber = tvUnitsNumber;
    }

    public void setTvUnitPrice(int tvUnitPrice) {
        this.tvUnitPrice = tvUnitPrice;
    }

    public void setTvTotalAmount(int tvTotalAmount) {
        this.tvTotalAmount = tvTotalAmount;
    }

    public void setTvShowUnit(String tvShowUnit) {
        this.tvShowUnit = tvShowUnit;
    }


//generating getters
    public String getTvLocation() {
        return tvLocation;
    }

    public String getTvHouseType() {
        return tvHouseType;
    }

    public int getTvUnitsNumber() {
        return tvUnitsNumber;
    }

    public int getTvUnitPrice() {
        return tvUnitPrice;
    }

    public int getTvTotalAmount() {
        return tvTotalAmount;
    }

    public String getTvShowUnit() {
        return tvShowUnit;
    }
}


