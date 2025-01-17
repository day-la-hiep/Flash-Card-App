package com.noface.flashcard;

import com.noface.flashcard.cardLibrary.CardLibraryController;
import com.noface.flashcard.screenNavigation.MainController;
import com.noface.flashcard.userUtilities.UserUtilitiesController;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      ResourceLoader.getInstance();

      UserUtilitiesController userUtilitiesController = new UserUtilitiesController();

      CardLibraryController cardLibraryController = new CardLibraryController();

      MainController mainController = new MainController(cardLibraryController, userUtilitiesController);
      mainController.setMainStage(primaryStage);

      userUtilitiesController.setMainController(mainController);

      Parent root = userUtilitiesController.getLoginScreen().getRoot();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setOnCloseRequest(e -> {
         mainController.endSession();
      });
   }
}