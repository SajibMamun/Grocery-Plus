package com.example.groceryplus.Models;

public class ProfileModel {
    String name;
    String email;
    String address;
    String contact;

    public ProfileModel() {
    }

    public ProfileModel(String name, String email, String address, String contact) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
