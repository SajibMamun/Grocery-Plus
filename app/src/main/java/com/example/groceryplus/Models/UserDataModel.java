package com.example.groceryplus.Models;

public class UserDataModel {
    String name;
    String email;
    String password;
    String profileImg;


    public UserDataModel() {
    }

    public UserDataModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
