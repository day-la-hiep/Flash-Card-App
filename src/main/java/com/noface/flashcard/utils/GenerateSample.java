package com.noface.flashcard.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.model.User;

public class GenerateSample {
   public void generateSampleUserFile() throws FileNotFoundException, IOException {
      Map<String, String> userPasswords = new HashMap<>();
      for (int i = 0; i < 10; i++) {
         String username = String.format("user%03d", i + 1);
         String password = username;
         userPasswords.put(username, password);
         // User user = new User(username, password);
         // Map<String, List<Card>> cards = new HashMap<>();
         // user.setCards(cards);
         // for(int j = 0; j < 10; j ++){
         // String topic = String.format("Topic %d", j + 1);
         // List<Card> cardsByTopic = new ArrayList<>();
         // user.getCards().put(topic, cardsByTopic);
         // for(int k = 0; k < 10; k++){
         // Card card = new Card(
         // String.format("FC %s %d %d", username, topic,
         // String.format("%d", k + 1)),
         // String.format("FC %s %d %d", username, topic,
         // String.format("%d", k + 1)),
         // LocalDateTime.now().toString()
         // );
         // cardsByTopic.add(card);
         // }
         // }
      }
         ObjectOutputStream oos = new ObjectOutputStream(
               new FileOutputStream("account-data//login-info.dat"));
      oos.writeObject(userPasswords);
   }

   public void generateUser(String username) {
      User user = new User(username, "123456");
      Map<String, List<Card>> cards = new HashMap<>();
      for (int i = 0; i < 10; i++) {
         String topic = String.format("Topic %d", i + 1);
         List<Card> cardsByTopic = new ArrayList<>();
         cards.put(topic, cardsByTopic);
         for (int j = 0; j < 10; j++) {
            Card card = new Card(
                  String.format("front content %d %d", i + 1, j + 1),
                  String.format("back content %d %d", i + 1, j + 1),
                  LocalDateTime.now().toString());
            cardsByTopic.add(card);
         }
      }
      user.setCards(cards);
      FileLoader loader = new FileLoader();
      ResourceLoader.getInstance().saveUserData(user);

   }

   public String readSampleUser(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username));
      User user = (User) ois.readObject();
      return user.toString();
   }

}
