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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class CardLibraryController {
   private CardLibraryScreen screen;
   private CardLearningController cardLearningController;
   private Map<String, List<Card>> data;
   private ListProperty<StringProperty> topicProperties = new SimpleListProperty<>(FXCollections.observableArrayList());
   private ListProperty<Card> cardProperties = new SimpleListProperty<>(FXCollections.observableArrayList());
   private String currentTopic;
   public CardLibraryController() throws IOException{
      cardLearningController = new CardLearningController();
      screen = new CardLibraryScreen(this, cardLearningController);
      
   }
   public CardLibraryScreen getScreen() {
      return screen;
   }  
   public ListProperty<StringProperty> getTopicProperties() {
      return topicProperties;
   }
   public ListProperty<Card> getCardProperties() {
      return cardProperties;
   }
   public void loadData(){
      data = ResourceLoader.getInstance().getData();
      for(String topic : data.keySet()){
         topicProperties.add(new SimpleStringProperty(topic));
      }
      currentTopic = topicProperties.getFirst().get();
      cardProperties.addAll(data.get(currentTopic));
   }
   public void setCardsByTopic(String name) {
      currentTopic = name;
      cardProperties.clear();
      cardProperties.addAll(data.get(name));
   }

   public void startLearn() {
      cardLearningController.startLearn(cardProperties);
   }
   public void renameCurrentTopicTo(String newValue) throws Exception{
      for(StringProperty name : topicProperties.get()){
         if(newValue.equals(name.get())){
            throw new Exception("Name duplicated");
         }
      }
      for(StringProperty name : topicProperties.get()){
         if(currentTopic.equals(name.get())){
            name.set(newValue);
         }
      }
      data.put(newValue, data.get(currentTopic));
      data.remove(currentTopic);
   }

   public void removeCurrentTopic(){
      data.remove(currentTopic);
      for(StringProperty topicProperty : topicProperties){
         if(topicProperty.get().equals(currentTopic)){
            topicProperties.remove(topicProperty);
            break;
         }
      }
   }
}
