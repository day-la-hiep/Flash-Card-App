package com.noface.flashcard.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.model.Card;

public class ResourceLoader {
    private static ResourceLoader resourceLoader;
    List<Card> cards = new ArrayList<>();
    List<String> topics = new ArrayList<>();
    Map<String, List<Card>> data;
    public static ResourceLoader getInstance(){
        if(resourceLoader == null){
            resourceLoader = new ResourceLoader();
            for(int i = 0; i < 10; i++){
                Card card = new Card(
                        String.format("Card %d", i + 1),
                        String.format("Front content %d", i + 1),
                        String.format("Back content %d", i + 1),
                        "Topic", LocalDateTime.now().minusDays(i).toString());
                resourceLoader.cards.add(card);
            }
            resourceLoader.data = new HashMap<>();
            for(int i = 0; i < 10; i++){
                String topic = String.format("Topic %d", i + 1);
                resourceLoader.topics.add(topic);
                Map<String, List<Card>> tmp = resourceLoader.data;
                tmp.put(topic, new ArrayList<>());
                for(int j = 0; j < 10; j++){
                    Card card = new Card(
                        String.format("Card %d", i + 1),
                        String.format("Front content %d", i + 1),
                        String.format("Back content %d", i + 1),
                        topic, LocalDateTime.now().minusDays(i).toString());
                    tmp.get(topic).add(card);
                }
            }
        }
        return resourceLoader;
    }
    public List<Card> getSampleCards(){

        return cards;
    }
   public Collection<? extends String> getSampleTopics() {
        return topics;
    }
    public Map<String, List<Card>> getData(){
        return data;
    }
}
