package com.noface.flashcard;

import com.noface.flashcard.controller.CardLearningController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardLearningController controller = new CardLearningController();
        Scene scene = new Scene(controller.getScreen().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
