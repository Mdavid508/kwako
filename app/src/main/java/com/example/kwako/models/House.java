package com.example.kwako.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House implements Parcelable {
    private String name, location, sellerName, houseType;
    private User owner;
    private double price;
    private double lat, lon;
    private boolean available;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
    public void setImages(List<Image> images){
        this.images = images;
    }
    public List<Image> getImages() {
        return images;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void getLat(double lon) {
        this.lon = lon;
    }


    public double getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> houseDetails = new HashMap<>();
        houseDetails.put("name", name);
        houseDetails.put("location", location);
        houseDetails.put("price", price);
        houseDetails.put("sellerName", sellerName);
        houseDetails.put("houseType", houseType);
        houseDetails.put("images", images);
        houseDetails.put("lat", lat);
        houseDetails.put("lon", lon);
        houseDetails.put("owner", owner);
        return houseDetails;
    }

    /**
     * Methods required for parcelable
     * De-constructing a parcel object
    */
    protected House(Parcel in){
        name= in.readString();
        location= in.readString();
        sellerName = in.readString();
        houseType = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        price = in.readDouble();
        lat = in.readDouble();
        lon = in.readDouble();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            available = in.readBoolean();
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(getName());
        parcel.writeString(location);
        parcel.writeString(sellerName);
        parcel.writeString(houseType);
        parcel.writeParcelable(owner, flags);
        parcel.writeDouble(price);
        parcel.writeDouble(lat);
        parcel.writeDouble(lon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(available);
        }
    }

    public static Creator<House> CREATOR = new Creator<House>() {
        @Override
        public House createFromParcel(Parcel in) {
            return new House(in);
        }

        @Override
        public House[] newArray(int size) {
            return new House[size];
        }
    };


}
