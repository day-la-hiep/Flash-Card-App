package com.noface.flashcard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.model.User;
import com.noface.flashcard.utils.ResourceLoader;

public class GenerateSampleData {
    public static void main(String[] args) {
        generateSampleUser();
    }

    public static void generateSampleUser() {
        User user = new User("user001", "user001");
        Map<String, List<Card>> cards = new HashMap<>();
        user.setCards(cards);
        for (int i = 0; i < 10; i++) {
            String topic = String.format("%s Topic %d", user.getUsername(),  i + 1);
            List<Card> cardsByTopic = new ArrayList<>();
            cards.put(topic, cardsByTopic);
            for (int j = 0; j < 10; j++) {
                Card card = new Card(
                        String.format("%s BC %d", topic,  j + 1),
                        String.format("%s FC %d", topic, j + 1),
                        LocalDateTime.now().toString());
                cardsByTopic.add(card);
            }
        }
        System.out.println(user);
        ResourceLoader.getInstance().saveUserData(user);

    }
}
