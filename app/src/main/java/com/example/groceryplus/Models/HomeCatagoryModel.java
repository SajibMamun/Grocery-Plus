package com.example.groceryplus.Models;

public class HomeCatagoryModel {
    String name;
    String img_url;
    String type;

    public HomeCatagoryModel()
    {}

    public HomeCatagoryModel(String name, String img_url, String type) {
        this.name = name;
        this.img_url = img_url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
