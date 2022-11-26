package com.example.groceryplus.API;

public class ProductColor {
    private String hex_value;
    private String colour_name;

    public ProductColor() {
    }

    public ProductColor(String hex_value, String colour_name) {
        this.hex_value = hex_value;
        this.colour_name = colour_name;
    }

    public String getHex_value() {
        return hex_value;
    }

    public void setHex_value(String hex_value) {
        this.hex_value = hex_value;
    }

    public String getColour_name() {
        return colour_name;
    }

    public void setColour_name(String colour_name) {
        this.colour_name = colour_name;
    }
}
