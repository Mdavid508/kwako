package com.example.kwako.models;

public class Image {
    String imageUrl, imageName;

    public Image(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public Image(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
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
}
