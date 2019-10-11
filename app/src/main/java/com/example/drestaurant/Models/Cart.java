package com.example.drestaurant.Models;

public class Cart {

    private String fid,price,quantity,title;

    public Cart() {
    }

    public Cart(String fid, String price, String quantity, String title) {
        this.fid = fid;
        this.price = price;
        this.quantity = quantity;
        this.title = title;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
