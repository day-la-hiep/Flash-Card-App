
package com.noface.flashcard.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.noface.flashcard.controller.CardLearningController;
import com.noface.flashcard.model.Card;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;


public class CardLearningScreen {

    @FXML
    private VBox root;
    @FXML
    private WebView frontView;
    @FXML
    private WebView backView;
    @FXML
    private Button cardEditButton;
    @FXML
    private VBox mainLearningArea;
    @FXML
    private Label doneLearningLabel;
    @FXML
    private Button showAnswerButton;
    @FXML
    private HBox doneButtonBar;
    private MainScreen mainScreen;



    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    private FXMLLoader loader;
    public <T> T getRoot(){
        return loader.getRoot();
    }
    ObjectProperty<Card> card = new SimpleObjectProperty<>();
    Binding<String> frontContent = Bindings.createObjectBinding(() -> {
        if(card.get() == null){
            return null;
        }
        return card.get().getFrontContent();
    }, card);
    Binding<String> backContent = Bindings.createObjectBinding(() -> {
        if(card.get() == null){
            return null;
        }
        return card.get().getBackContent();
    }, card);
    private CardLearningController controller;
    public CardLearningScreen(CardLearningController controller) throws IOException {
        this.controller = controller;
        card.bind(controller.cardPropertyProperty());
        loader = new FXMLLoader(this.getClass().getResource("CardLearningScreen.fxml"));
        loader.setController(this);
        loader.load();
    }

    @FXML
    public void initialize(){
        addCustomScreenComponent();
    }

    public final long[] repetitionTimes = {60, 360, 600, 3 * 24 * 60 * 60};
    private List<Button> selectRepetitionButtons = new ArrayList<>();
    public final String[] repetitionLabels = {"Again - 1 minutes", "Hard - 6 minutes", "Good - 10 minutes", "Easy - 3 days"};
    public void addCustomScreenComponent(){
        for (int i = 0; i < repetitionTimes.length; i++) {
            String label = repetitionLabels[i];
            Button selectRepetitionButton = new Button(label);
            selectRepetitionButtons.add(selectRepetitionButton);
            doneButtonBar.getChildren().add(selectRepetitionButton);
            HBox.setMargin(selectRepetitionButton, new Insets(0, 5, 0, 5));
            selectRepetitionButton.setOnAction(controller.handleSelectRepetitionButtonClicked(
                    repetitionTimes[i]));
        }
    }

}
