package com.noface.flashcard.cardLibrary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.noface.flashcard.model.Card;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditCardDialog{
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
         try{
            LocalDateTime newDueTime = dueDatePicker.getValue().atStartOfDay();

            card.dueTimeProperty().set(newDueTime.toString());;
            card.frontContentProperty().set(frontContent.getText());
            card.backContentProperty().set(backContent.getText());
            closeWindow();
         }catch(Exception exception){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid information");
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

}
