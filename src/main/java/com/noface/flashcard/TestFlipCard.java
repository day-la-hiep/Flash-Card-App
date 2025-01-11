package com.noface.flashcard;

import java.util.List;

import com.noface.flashcard.cardLearning.CardLearningController;
import com.noface.flashcard.model.Card;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFlipCard extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardLearningController controller = new CardLearningController();
        Scene scene = new Scene(controller.getScreen().getRoot());
        List<Card> data = ResourceLoader.getInstance().getSampleCards();
        controller.startLearn(data);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
