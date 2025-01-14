package com.noface.flashcard.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.model.User;

public class ResourceLoader {
    private User user;
    private static ResourceLoader resourceLoader;
    FileLoader fileLoader = new FileLoader();

    public static ResourceLoader getInstance() {
        if(resourceLoader == null){
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }



    public User getUserData(String username){
        try {
            return fileLoader.readUser(username);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setUser(User user){
        this.user = user;
    }



    public List<Card> getSampleCards() {
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Card card = new Card(
                String.format("Front content %d", i + 1),
                String.format("Back content %d", i + 1),
                LocalDateTime.now().toString()
            );
        }
        return cards;
    }

}
