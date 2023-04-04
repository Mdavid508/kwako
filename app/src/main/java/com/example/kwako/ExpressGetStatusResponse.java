package com.example.kwako;

import com.google.gson.annotations.SerializedName;

public class ExpressGetStatusResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("amount")
    private int amount;

    @SerializedName("msisdn")
    private String msisdn;

    @SerializedName("sync_count")
    private int syncCount;

    @SerializedName("is_complete")
    private int isComplete;

    @SerializedName("sync_status")
    private String syncStatus;

    @SerializedName("link_id")
    private String linkId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("supporter_id")
    private String supporterId;

    @SerializedName("external_reference")
    private String externalReference;

    @SerializedName("mpesa_receipt")
    private String mpesaReceipt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getSyncCount() {
        return syncCount;
    }

    public void setSyncCount(int syncCount) {
        this.syncCount = syncCount;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getMpesaReceipt() {
        return mpesaReceipt;
    }

    public void setMpesaReceipt(String mpesaReceipt) {
        this.mpesaReceipt = mpesaReceipt;
    }
// Add getters and setters for all fields here
}