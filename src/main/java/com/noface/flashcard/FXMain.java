package com.noface.flashcard;

import com.noface.flashcard.controller.LoginScreenController;
import com.noface.flashcard.controller.MainController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMain extends Application{

    public void start(Stage stage) throws Exception {
        LoginScreenController loginScreenController = new LoginScreenController();
        Scene scene = new Scene(loginScreenController.getScreen().getRoot());
        stage.setScene(scene);
        stage.show();
        loginScreenController.setMainController(new MainController());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
