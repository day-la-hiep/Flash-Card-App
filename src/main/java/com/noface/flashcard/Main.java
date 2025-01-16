package com.noface.flashcard;

import com.noface.flashcard.cardLibrary.CardLibraryController;
import com.noface.flashcard.screenNavigation.MainController;
import com.noface.flashcard.userUtilities.LoginRegisterController;
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
      // LoginScreenController controller = new LoginScreenController();
      // Parent root = controller.getScreen().getRoot();
      // Scene scene = new Scene(root);
      // primaryStage.setScene(scene);
      // primaryStage.show();
      ResourceLoader.getInstance();

      LoginRegisterController loginRegisterController = new LoginRegisterController();

      CardLibraryController cardLibraryController = new CardLibraryController();

      MainController mainController = new MainController(cardLibraryController, loginRegisterController);
      mainController.setMainStage(primaryStage);

      loginRegisterController.setMainController(mainController);

      Parent root = loginRegisterController.getLoginScreen().getRoot();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}