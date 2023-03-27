package com.example.kwako;

public class CustomerPaymentItem {
     String tvLocation;
     String tvHouseType;
     String tvUnitsNumber;
     String tvUnitPrice;
     String tvTotalAmount;
     String tvShowUnit;

    public CustomerPaymentItem(String tvLocation, String tvHouseType, String tvUnitsNumber, String tvUnitPrice, String tvTotalAmount, String tvShowUnit) {
        this.tvLocation=tvLocation;
        this.tvHouseType= tvHouseType;
        this.tvUnitsNumber= tvUnitsNumber;
        this.tvUnitPrice = tvUnitPrice;
        this.tvTotalAmount = tvTotalAmount;
        this.tvShowUnit = tvShowUnit;
    }



    public void setTvLocation(String tvLocation) {
        this.tvLocation = tvLocation;
    }

    public void setTvHouseType(String tvHouseType) {
        this.tvHouseType = tvHouseType;
    }

    public void setTvUnitsNumber(int tvUnitsNumber) {
        this.tvUnitsNumber = String.valueOf(tvUnitsNumber);
    }

    public void setTvUnitPrice(int tvUnitPrice) {
        this.tvUnitPrice = String.valueOf(tvUnitPrice);
    }

    public void setTvTotalAmount(int tvTotalAmount) {
        this.tvTotalAmount = String.valueOf(tvTotalAmount);
    }

    public void setTvShowUnit(String tvShowUnit) {
        this.tvShowUnit = tvShowUnit;
    }

    //generating of getters and setters
    public String getTvLocation() {
        return tvLocation;
    }

    public String getTvHouseType() {
        return tvHouseType;
    }

    public int getTvUnitsNumber() {
        return Integer.parseInt(tvUnitsNumber);
    }

    public int getTvUnitPrice() {
        return Integer.parseInt(tvUnitPrice);
    }

    public int getTvTotalAmount() {
        return Integer.parseInt(tvTotalAmount);
    }

    public String getTvShowUnit() {
        return tvShowUnit;
    }
}
