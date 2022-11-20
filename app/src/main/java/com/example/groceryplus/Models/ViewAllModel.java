package com.example.groceryplus.Models;

public class ViewAllModel {

    String name;
    String description;
    String rating;
    String type;
    int price;
    String img_url;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String rating, String type, int price, String img_url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.type = type;
        this.price = price;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
