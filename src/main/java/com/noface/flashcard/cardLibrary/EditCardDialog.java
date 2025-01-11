package com.noface.flashcard.cardLibrary;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.noface.flashcard.model.Card;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class EditCardDialog extends Dialog<Card> {
   public EditCardDialog(Card card) {
      this.setTitle("Edit card");
      TextArea frontContent = new TextArea();
      TextArea frontField = new TextArea();
      frontField.setPromptText("Mặt trước");
      if (card != null)
         frontField.setText(card.getFrontContent());

      TextArea backField = new TextArea();
      backField.setPromptText("Mặt sau");
      if (card != null)
         backField.setText(card.getBackContent());

      // DateTimPicker dueDatePicker = new DatePicker();
      // if (card != null && card.getDueTime() != null) {
      //    dueDatePicker.setValue(LocalDateTime.parse(card.getDueTime()));
      // }

      // Sắp xếp layout
      VBox content = new VBox(10,
            new Label("Mặt trước:"), frontField,
            new Label("Mặt sau:"), backField);
      this.getDialogPane().setContent(content);

      // Nút Save và Cancel
      ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
      this.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

      this.setResultConverter(dialogButton -> {
         if (dialogButton == saveButtonType) {
            String front = frontField.getText().trim();
            String back = backField.getText().trim();
            card.frontContentProperty().set(front);
            card.backContentProperty().set(back);
         }
         return null;
      });
   }
}
