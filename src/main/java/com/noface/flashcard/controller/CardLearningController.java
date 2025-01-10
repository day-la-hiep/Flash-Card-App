package com.noface.flashcard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.view.CardLearningScreen;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CardLearningController {
    private List<Card> cards = new ArrayList<>();
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

    public ObjectProperty<Card> cardPropertyProperty() {
        return cardProperty;
    }

    public EventHandler<ActionEvent> handleSelectRepetitionButtonClicked(long repetitionTime) {
        Card card = cardProperty.get();
        card.setDueTime(card.getDueTime() + repetitionTime);
    }
}
