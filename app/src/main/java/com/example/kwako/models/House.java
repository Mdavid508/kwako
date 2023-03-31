package com.example.kwako.models;

import java.util.HashMap;
import java.util.Map;

public class House {
    private String name, location, price, sellerName;
    private boolean available;
    public House(){
        // required empty default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> houseDetails = new HashMap<>();
        houseDetails.put("name", name);
        houseDetails.put("location", location);
        houseDetails.put("price", price);
        houseDetails.put("sellerName", sellerName);

        return houseDetails;
    }
}
