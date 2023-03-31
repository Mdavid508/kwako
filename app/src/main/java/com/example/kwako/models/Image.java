package com.example.kwako.models;

import android.net.Uri;

public class Image {
    String imageUrl, imageName;
    Uri imageUri;

    public Image(){
        // required default constructor
    }

    public Image(String imageName){
        this.imageName = imageName;
    }
    public Image(String imageName, Uri imageUri) {
        this.imageName = imageName;
        this.imageUri = imageUri;
    }
    public Image(String imageName, Uri imageUri, String imageUrl) {
        this.imageName = imageName;
        this.imageUri = imageUri;
        this.imageUrl = imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public Uri getImageUri() {
        return imageUri;
    }
    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
