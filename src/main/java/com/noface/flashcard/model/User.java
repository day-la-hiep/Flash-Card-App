package com.noface.flashcard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String username;
    private String password;
    private Map<String, List<Card>> cards;

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

}
