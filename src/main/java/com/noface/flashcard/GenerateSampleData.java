package com.noface.flashcard;

import java.io.IOException;
import java.time.LocalDate;
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
        generateSampleData();
    }

    public static void generateSampleData(){
        Map<String, String> userPasswords = new HashMap<>();
        for(int i = 0; i < 10; i++){
            String name = "Name " + i;
            String username = String.format("username%03d", i + 1);
            String password = username;
            userPasswords.put(username, password);
            String email = username + "gmail.com";
            LocalDate dob = LocalDate.now();
            String phoneNumber = "Phone number " + (i + 1);
            String gender = "male";
            Map<String, List<Card>> cards = new HashMap<>();
            for(int j = 0; j < 10; j++){
                String topic = String.format("Topc %d", j + 1);
                List<Card> cardsByTopic = new ArrayList<>();
                cards.put(topic, cardsByTopic);
                for(int k = 0; k < 10; k++){
                    Card card = new Card(
                        String.format("%s %s FC %d", username, topic, k + 1),
                        String.format("%s %s BC %d", username, topic, k + 1),
                        LocalDateTime.now().toString()
                    );
                    cardsByTopic.add(card);
                }
            } 
            User user = new User(name, username, password, dob, email, gender, phoneNumber, cards);
            try {
                ResourceLoader.getInstance().updateUserPassword(username, password);
                ResourceLoader.getInstance().createNewAccountData(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
