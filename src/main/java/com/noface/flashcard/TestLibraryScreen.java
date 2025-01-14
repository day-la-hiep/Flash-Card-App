package com.noface.flashcard;

import com.noface.flashcard.cardLibrary.CardLibraryController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLibraryScreen extends Application{

   @Override
   public void start(Stage stage) throws Exception {
      CardLibraryController controller = new CardLibraryController();
      Scene scene = new  Scene(controller.getScreen().getRoot());
      stage.setScene(scene);
      stage.show();
      controller.loadData("KhongPhaiLaHiep");
   }
   public static void main(String[] args) {
      launch(args);
   }
   
}
