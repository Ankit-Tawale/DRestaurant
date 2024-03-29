package com.example.drestaurant.Models;

public class FoodModel {
    private String image;
    private String title;
    private String description;
    private String price;
    private String fid;

    public FoodModel() {
    }

    public FoodModel(String image, String title, String description, String price, String fid) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.fid = fid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
