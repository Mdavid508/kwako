package com.example.kwako.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class User implements Parcelable {
    private String username, email, phoneNumber, whatsAppNumber, userType;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", email);
        userData.put("phoneNumber", phoneNumber);
        userData.put("whatsAppNumber", whatsAppNumber);
        userData.put("userType", userType);
        return userData;
    }


    /**
     * Required for passing data via intent as parcelable
     */

    public User(Parcel in) {
        username = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        whatsAppNumber = in.readString();
        userType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
         parcel.writeString(username);
         parcel.writeString(email);
         parcel.writeString(phoneNumber);
         parcel.writeString(whatsAppNumber);
         parcel.writeString(userType);
    }

    public static Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
