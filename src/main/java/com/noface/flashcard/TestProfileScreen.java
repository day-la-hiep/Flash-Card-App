package com.noface.flashcard;

import com.noface.flashcard.userUtilities.ProfileScreen;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestProfileScreen extends Application {
    public static void main(String[] args) {
       launch(args); 
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ProfileScreen profileScreen = new ProfileScreen(null);
        Parent node = profileScreen.getRoot();
        Scene scene = new Scene(node);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
