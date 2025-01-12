package com.noface.flashcard.cardLibrary;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.noface.flashcard.model.Card;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCardDialog {
   private Card card;
   @FXML 
   private TextArea frontContent;
   @FXML
   private TextArea backContent;
   @FXML
   private DatePicker dueDatePicker;
   @FXML
   private Button saveButton;
   @FXML
   private Button cancelButton;
   private FXMLLoader loader;
   public EditCardDialog() throws IOException{
      loader = new FXMLLoader(this.getClass().getResource("CardEditingScreen.fxml"));
      loader.setController(this);
      loader.load();
   }
   @FXML
   public void initialize(){
      
      saveButton.setOnAction(e -> {
         LocalDateTime newDueTime = dueDatePicker.getValue().atStartOfDay();

         card.dueTimeProperty().set(newDueTime.toString().trim());;
         card.frontContentProperty().set(frontContent.getText().trim());
         card.backContentProperty().set(backContent.getText().trim());
         if(card.dueTimeProperty().get() != "" && card.backContentProperty().get() != ""
               && card.frontContentProperty().get() != ""){
                  closeWindow();
         }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid card property, please try again");
            alert.show();
         }
      });

      cancelButton.setOnAction(e -> {
         closeWindow();
      });
   }
   public void closeWindow(){
      Scene scene = cancelButton.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.close();
   }
   public void setCard(Card card){
      this.card = card;
      frontContent.setText(card.getFrontContent());
      backContent.setText(card.getBackContent());
      LocalDateTime cardDueTime = LocalDateTime.parse(card.getDueTime());
      LocalDate date = LocalDate.of(cardDueTime.getYear(), cardDueTime.getMonthValue(), cardDueTime.getDayOfMonth());
      dueDatePicker.setValue(date);
   }
   public <T> T getRoot(){
      return loader.getRoot();
   }
   public void showAndWait(){
      Parent root = getRoot();
      if(root.getScene() == null){
         Scene scene = new Scene(root);
      }
      root.getScene().setOnKeyPressed(e -> {
         if(e.getCode() == KeyCode.ESCAPE){
            closeWindow();
         }else if(e.getCode() == KeyCode.ENTER){
            saveButton.fire();
         }
      });
      Stage stage = new Stage();
      stage.setScene(root.getScene());
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
   }

   

}
