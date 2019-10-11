package com.example.drestaurant.Models;

public class Model {
    private int image;
    private String title;
    private String desp;

    public Model(int image, String title, String desp) {
        this.image = image;
        this.title = title;
        this.desp = desp;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
