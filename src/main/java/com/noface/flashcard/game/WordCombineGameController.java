package com.noface.flashcard.game;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.noface.flashcard.cardLibrary.CardLibraryController;
import com.noface.flashcard.model.Card;
import javafx.util.Pair;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordCombineGameController {
    private CardLibraryController cardLibraryController;
    private WordCombineGameScreen screen;
    private SimpleListProperty<Pair<String, Card>> words = new SimpleListProperty<>(FXCollections.observableArrayList());

    public WordCombineGameController(CardLibraryController cardLibraryController) throws IOException {
        this.cardLibraryController = cardLibraryController;
        try {
            words.get().clear();
            getWordsData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        screen = new WordCombineGameScreen(this);
    }

    public WordCombineGameScreen getScreen() {
        return screen;
    }

    public void getWordsData() throws URISyntaxException {
        List<Pair<String, Card>> cardList = new ArrayList<>();

        for(Map.Entry<String, List<Card>> entry: cardLibraryController.getData().entrySet()){
            for(Card card : entry.getValue()){
                cardList.add(new Pair<>(entry.getKey(), card));
            }
        }

        words.addAll(cardList);
    }

    public ObservableList<Pair<String, Card>> getWords() {
        return words.get();
    }

    public SimpleListProperty<Pair<String, Card>> wordsProperty() {
        return words;
    }

    public void refresh() {
    }
}
