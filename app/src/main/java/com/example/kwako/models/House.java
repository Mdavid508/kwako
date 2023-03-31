package com.example.kwako.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House {
    private String name, location, price, available, sellerName;
    private List<Image> images;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public void setImages(List<Image> images){
        this.images = images;
    }
    public List<Image> getImages() {
        return images;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> houseDetails = new HashMap<>();
        houseDetails.put("name", name);
        houseDetails.put("location", location);
        houseDetails.put("price", price);
        houseDetails.put("sellerName", sellerName);
        houseDetails.put("images", images);

        return houseDetails;
    }
}
