package com.noface.flashcard;

import com.noface.flashcard.cardLearning.CardLearningController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardLearningController controller = new CardLearningController();
        Scene scene = new Scene(controller.getScreen().getRoot());
        controller.startLearn();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
