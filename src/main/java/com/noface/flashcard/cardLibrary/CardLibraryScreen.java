package com.noface.flashcard.cardLibrary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.noface.flashcard.model.Card;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;

public class CardLibraryScreen implements Initializable{
   

   private FXMLLoader loader;
   private CardLibraryController controller;
   @FXML
   private TableView<String> topicTable;
   @FXML
   private TableView<Card> cardTable;

   public CardLibraryScreen(CardLibraryController cardLibraryController) throws IOException {
      this.controller = cardLibraryController;
      loader = new FXMLLoader(this.getClass().getResource("CardLibraryScreen.fxml"));
      loader.setController(this);
      loader.load();
   }


   @Override
   public void initialize(URL location, ResourceBundle resources) {


      bindingTableToController(controller);

   }
   public void bindingTableToController(CardLibraryController controller){
      topicTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      cardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      topicTable.setItems(controller.getTopicProperties());
      cardTable.setItems(controller.getCardProperties());

      cardTable.getColumns().clear();
      TableColumn<Card, String> frontContentColumn = new TableColumn<>("Front content");
      frontContentColumn.setCellValueFactory(cellData -> {
         Card card = cellData.getValue();
         return card.frontContentProperty();
      });

      TableColumn<Card, String> backContentColumn = new TableColumn<>("Back content");
      backContentColumn.setCellValueFactory(cellData -> {
         Card card = cellData.getValue();
         return card.backContentProperty();
      });

      TableColumn<Card, Button> editButtonColumn = new TableColumn<>("");
      editButtonColumn.setPrefWidth(50);  
      editButtonColumn.setResizable(false);
      editButtonColumn.setCellValueFactory(cellData -> {
         Button button = new Button("Edit");
         button.setMaxWidth(Double.MAX_VALUE);
         button.setMaxHeight(Double.MAX_VALUE);
         button.setOnAction(e -> {
            EditCardDialog dialog = new EditCardDialog(cellData.getValue());
            dialog.showAndWait();
            
         });
         return new SimpleObjectProperty<>(button);
      });

      cardTable.getColumns().addAll(frontContentColumn, backContentColumn, editButtonColumn);

      topicTable.getColumns().clear();

      TableColumn<String, String> topicColumn = new TableColumn<>("name");
      topicColumn.setCellValueFactory(cellData -> {
         return new SimpleStringProperty(cellData.getValue());
      });

      topicColumn.setCellFactory(column -> new TableCell<String, String>() {
         @Override
         protected void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
            if (empty || name == null) {
                  setText(null);
            } else {
                  setText(name);
                  setOnMouseClicked(event -> {
                     controller.setCardsByTopic(name);
                  });
            }
            setAlignment(Pos.CENTER);
         }
      });

      topicTable.getColumns().add(topicColumn);
   }

   public <T> T getRoot() {
      return loader.getRoot();
   }
}
