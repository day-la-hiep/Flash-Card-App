package com.noface.flashcard.cardLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noface.flashcard.cardLearning.CardLearningController;
import com.noface.flashcard.model.Card;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class CardLibraryController {
   private CardLibraryScreen screen;
   private CardLearningController cardLearningController;
   private Map<String, List<Card>> data;
   private ListProperty<String> topicProperties = new SimpleListProperty<>(FXCollections.observableArrayList());
   private ListProperty<Card> cardProperties = new SimpleListProperty<>(FXCollections.observableArrayList());
   public CardLibraryController() throws IOException{
      cardLearningController = new CardLearningController();
      screen = new CardLibraryScreen(this, cardLearningController);
      
   }
   public CardLibraryScreen getScreen() {
      return screen;
   }  
   public ListProperty<String> getTopicProperties() {
      return topicProperties;
   }
   public ListProperty<Card> getCardProperties() {
      return cardProperties;
   }
   public void loadData(){
      data = ResourceLoader.getInstance().getData();
      topicProperties.addAll(data.keySet());
      cardProperties.addAll(data.get(topicProperties.getFirst()));
   }
   public void setCardsByTopic(String name) {
      cardProperties.clear();
      cardProperties.addAll(data.get(name));
   }

   public void refreshCard(Card card){
      cardProperties.remove(card);
      cardProperties.add(card);
   }
   public void startLearn() {
      cardLearningController.startLearn(cardProperties);
   }
   
}
