package com.noface.flashcard.cardLearning;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CardLearningController {
    private PriorityQueue<Card> cards = new PriorityQueue<>(Card.comparatorByDueTimeNearest());
    private ObjectProperty<Card> cardProperty = new SimpleObjectProperty<>();
    private CardLearningScreen cardLearningScreen;

    public CardLearningController() throws IOException {
        cardLearningScreen = new CardLearningScreen(this);
    }
    public void loadCardByTopic(String topicTitle){
    }
    public CardLearningScreen getScreen() {
        return cardLearningScreen;
    }

    public Card getCardProperty() {
        return cardProperty.get();
    }

    public ObjectProperty<Card> cardProperty() {
        return cardProperty;
    }

    public EventHandler<ActionEvent> updateCurrentCardDueTime(long repetitionTime) {
        Card card = cardProperty.get();
        card.setDueTime(LocalDateTime.now().plusSeconds(repetitionTime).toString());
        return null;
    }
    public void changeToNextCard() {
        cards.remove(cardProperty.get());
        while(cards.size() > 0){
            Card frontCard = cards.peek();
            if(LocalDateTime.parse(frontCard.getDueTime()).compareTo(LocalDateTime.now()) > 0){
                cards.remove();
            }else{
                break;
            }
        }
        System.out.println("Cards size:" + cards.size());
        if(cards.size() == 0){
            if(LocalDateTime.parse(cardProperty.get().getDueTime()).compareTo(LocalDateTime.now()) <= 0){
                cards.add(cardProperty.get());
            }else{
                cardProperty.set(null);
            }
        }else{
            Card cardToAdd = cards.peek();
            if(cardProperty.get() != null){
                cards.add(cardProperty.get());
            }
            cardProperty.set(cardToAdd);
        }
        System.out.println(cardProperty.get());
    }

    public void startLearn(List<Card> data){
        System.out.println("Start learn");
        System.out.println(data.size());
        cards.clear();
        cards.addAll(data);
        for(Card c : data){
            System.out.println(c);
        }
        changeToNextCard();
        cardLearningScreen.start();
    }

}
