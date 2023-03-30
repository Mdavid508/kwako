package com.example.kwako;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class DataModal {

    @SerializedName("msisdn")
    private  String msisdn;

    @SerializedName("amount")
    private int amount;

    @SerializedName("account_no")
    private String accountNo;

    public DataModal(String msisdn, int amount, String accountNo){
        this.msisdn = msisdn;
        this.amount = amount;
        this.accountNo = accountNo;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @NonNull
    @Override
    public String toString() {
        Gson gson = new Gson();
        JsonObject data = new JsonObject();
        data.addProperty("msisdn", msisdn);
        data.addProperty("amount", amount);
        data.addProperty("account_no", accountNo);
        return gson.toJson(data);
    }

    public JsonObject toJsonObject(){
        JsonObject data = new JsonObject();
        data.addProperty("msisdn", msisdn);
        data.addProperty("amount", amount);
        data.addProperty("account_no", accountNo);
        return data;
    }
}