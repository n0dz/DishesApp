package com.nodz.dishapp.Model;

public class DishModel {
    String name;
    String img;
    String details;

    public DishModel(String name, String img, String details) {
        this.name = name;
        this.img = img;
        this.details = details;
    }

    public DishModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}