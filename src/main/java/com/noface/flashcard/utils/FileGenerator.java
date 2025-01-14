package com.noface.flashcard.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.model.User;

public class FileGenerator {
   public void generateUser(String username){
      User user = new User(username, "123456");
      Map<String, List<Card>> cards = new HashMap<>();
      for(int i = 0; i < 10; i++){
         String topic = String.format("Topic %d", i + 1);
         List<Card> cardsByTopic = new ArrayList<>();
         cards.put(topic, cardsByTopic);
         for(int j = 0; j < 10; j++){
            Card card = new Card(
                  String.format("front content %d %d", i + 1,  j + 1),
                  String.format("back content %d %d", i + 1, j + 1),
                  LocalDateTime.now().toString()
            );
            cardsByTopic.add(card);
         }
      }
      user.setCards(cards);
      FileLoader loader = new FileLoader();
      loader.writeFile(user, user.getUsername());

   }

   public String readSampleUser(String username) throws FileNotFoundException, IOException, ClassNotFoundException{
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username));
      User user = (User) ois.readObject();
      return user.toString();
   } 

   
}
