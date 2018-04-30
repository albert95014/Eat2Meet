package com.example.jonathansun5.eatmeet;

import java.util.ArrayList;

// custom class made for storing a message. you can update this class
public class User {

    private String name;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private String numFriends;
    private ArrayList<String> friends;

    User(String email, String username, String birthdate, String password, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.birthYear = birthdate;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    User() {
        this.email= "hello world";
        this.username = "dummy";
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

}

