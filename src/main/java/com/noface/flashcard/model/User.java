package com.noface.flashcard.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String name;
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
    private String gender;
    private String phoneNumber;
    private Map<String, List<Card>> cards;
    

    public User(String name, String username, String password, LocalDate dob, String email, String gender,
            String phoneNumber, Map<String, List<Card>> cards) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.cards = cards;
    }




    public User(String username, String password) {
        this.password = password;
        this.username = username;
        cards = new HashMap<>();
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, List<Card>> getCards() {
        return cards;
    }

    public void setCards(Map<String, List<Card>> cards) {
        this.cards = cards;
    }
    public String toString(){
        String res = "";
        res = "User " + username + " " + password;
        res += "\n";
        for(String topic : cards.keySet()){
            res += ("Topic: " + topic + "\n");
            for(Card card : cards.get(topic)){
                res += card.toString() + "\n";
            }
        }
        return res;
    }


    public LocalDate getDob() {
        return dob;
    }


    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }

}
