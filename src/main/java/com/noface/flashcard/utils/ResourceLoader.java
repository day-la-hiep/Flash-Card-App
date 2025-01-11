package com.noface.flashcard.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.noface.flashcard.model.Card;

public class ResourceLoader {
    private static ResourceLoader resourceLoader;

    public static ResourceLoader getInstance(){
        if(resourceLoader == null){
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }
    public List<Card> getSampleCards(){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Card card = new Card(
                    String.format("Card %d", i + 1),
                    String.format("Front content %d", i + 1),
                    String.format("Back content %d", i + 1),
                    "Topic", LocalDateTime.now().minusDays(i).toString());
            cards.add(card);
        }
        return cards;
    }
}
