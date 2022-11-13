package com.example.groceryplus.Models;

public class NavCatagoryModel {
    String name;
    String description;
    String discount;
    String rating;
    String img_url;

    public NavCatagoryModel() {
    }

    public NavCatagoryModel(String name, String description, String discount, String rating, String img_url) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.rating = rating;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
