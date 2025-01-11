
package com.noface.flashcard.cardLearning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.noface.flashcard.model.Card;

import javafx.beans.binding.Binding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CardLearningScreen {

    @FXML
    private VBox root;
    @FXML
    private TextArea frontView;
    @FXML
    private TextArea backView;
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

    private FXMLLoader loader;
    public <T> T getRoot(){
        return loader.getRoot();
    }
    ObjectProperty<Card> card;
    Binding<String> frontContent;
    Binding<String> backContent;



    private CardLearningController controller;
    public CardLearningScreen(CardLearningController controller) throws IOException {
        this.controller = controller;
        loader = new FXMLLoader(this.getClass().getResource("CardLearningScreen.fxml"));
        loader.setController(this); 
        loader.load();
        createBinding();

    }

    @FXML
    public void initialize(){
        configCustomScreenComponent();
        frontView.setEditable(false);
        backView.setEditable(false);
    }

    public final long[] repetitionTimes = {60, 360, 600, 3 * 24 * 60 * 60};
    private List<Button> selectRepetitionButtons = new ArrayList<>();
    public final String[] repetitionLabels = {"Again - 1 minutes", "Hard - 6 minutes", "Good - 10 minutes", "Easy - 3 days"};
    public void configCustomScreenComponent(){
        for (int i = 0; i < repetitionTimes.length; i++) {
            String label = repetitionLabels[i];
            Button selectRepetitionButton = new Button(label);
            selectRepetitionButtons.add(selectRepetitionButton);
            doneButtonBar.getChildren().add(selectRepetitionButton);
            HBox.setMargin(selectRepetitionButton, new Insets(0, 5, 0, 5));
            selectRepetitionButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Button selected");
                    long addedTime = repetitionTimes[doneButtonBar.getChildren().indexOf(selectRepetitionButton)];
                    controller.updateCurrentCardDueTime(addedTime);
                    controller.changeToNextCard();
                    changeToBackCardHidedState();
                }
            });
            

            showAnswerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    changeToBackCardShowedState();
                }
                
            });
        }
    }

    public void createBinding(){
        card = new SimpleObjectProperty<>();
        card.bind(controller.cardProperty());
        card.addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                changeToDoneLearningState();
            }else{
                frontView.setText(newValue.getFrontContent());
                backView.setText(newValue.getBackContent());
            }
        });
    }

    public void changeToBackCardShowedState(){
        frontView.setVisible(true);
        backView.setVisible(true);
        doneButtonBar.setVisible(true);
        showAnswerButton.setVisible(false);
    }

    public void changeToBackCardHidedState(){
        frontView.setVisible(true);
        backView.setVisible(false);
        doneButtonBar.setVisible(false);
        showAnswerButton.setVisible(true);
    }
    public void changeToDoneLearningState(){
        System.out.println("Done learning");
        mainLearningArea.setVisible(false);
        doneLearningLabel.setVisible(true);
    }
    public void start(){
        changeToBackCardHidedState();
    }
}
