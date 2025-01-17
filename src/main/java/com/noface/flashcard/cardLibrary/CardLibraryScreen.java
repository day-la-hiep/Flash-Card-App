package com.noface.flashcard.cardLibrary;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import com.noface.flashcard.cardLearning.CardLearningController;
import com.noface.flashcard.model.Card;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CardLibraryScreen implements Initializable {

   private FXMLLoader loader;
   private CardLibraryController cardLibraryController;
   private CardLearningController cardLearningController;
   @FXML
   private TableView<StringProperty> topicTable;
   @FXML
   private TableView<Card> cardTable;
   @FXML
   private Button learnButton;
   @FXML
   private Button renameButton;
   @FXML
   private Button deleteTopicButton;
   @FXML
   private Button addTopicButton;
   private Button addCardButton;

   private EditCardDialog editCardDialog = new EditCardDialog();

   public CardLibraryScreen(CardLibraryController cardLibraryController, CardLearningController cardLearningController)
         throws IOException {
      this.cardLibraryController = cardLibraryController;
      this.cardLearningController = cardLearningController;
      loader = new FXMLLoader(this.getClass().getResource("CardLibraryScreen.fxml"));
      loader.setController(this);
      loader.load();
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      bindingTableToController(cardLibraryController);
      configureLearnButton();
      configureRenameTopicButton();
      configureAddTopicButton();
      configureDeleteTopicButton();
   }

   private void configureAddTopicButton() {

      addTopicButton.setOnAction(e -> {
         TextInputDialog dialog = showEnterNameDialog("");
         Optional<String> result = dialog.showAndWait();
         result.ifPresent(name -> {
            try {
               cardLibraryController.addNewTopic(name);
            } catch (Exception e1) {
               e1.printStackTrace();
            }
         });
      });
   }

   private void configureDeleteTopicButton() {
      deleteTopicButton.setOnAction(e -> {
         cardLibraryController.removeCurrentTopic();
         cardLibraryController.setCardsByTopic(topicTable.getSelectionModel().getSelectedItem().get());
      });
   }

   private void configureRenameTopicButton() {
      renameButton.setOnAction(e -> {
         StringProperty topicProperty = topicTable.getSelectionModel().getSelectedItem();
         if (topicProperty != null) {
            TextInputDialog dialog = showEnterNameDialog(topicProperty.get());
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
               try {
                  cardLibraryController.renameCurrentTopicTo(name);
                  topicProperty.set(name);
               } catch (Exception e2) {

               }
            });
         }
      });
   }

   private void configureLearnButton() {
      learnButton.setOnAction(e -> {
         cardLibraryController.startLearn();
         Parent root = cardLearningController.getScreen().getRoot();
         if (root.getScene() == null) {
            Scene scene = new Scene(root);
         }

         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.setScene(root.getScene());
         disableScreen();
         stage.showAndWait();
         showScreen();
      });
   }

   private void bindingTableToController(CardLibraryController controller) {
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

      // frontContentColumn.setCellFactory(column -> {
      // TableCell<StringProperty, String> cell = new TableCell<>();
      // });
      frontContentColumn.setCellFactory(column -> {
         TableCell<Card, String> cell = new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty || item == null) {
                  setText(null);
                  setGraphic(null);
               } else {
                  this.setAlignment(Pos.CENTER_LEFT);
                  setText(item);
               }
            }
         };
         return cell;
      });

      TableColumn<Card, String> backContentColumn = new TableColumn<>(
            "Back content");
      backContentColumn.setCellValueFactory(cellData -> {
         Card card = cellData.getValue();
         return card.backContentProperty();
      });
      backContentColumn.setCellFactory(column -> {
         TableCell<Card, String> cell = new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty || item == null) {
                  setText(null);
                  setGraphic(null);
               } else {
                  this.setAlignment(Pos.CENTER_LEFT);
                  setText(item);
               }
            }
         };
         return cell;
      });

      TableColumn<Card, String> dueTimeColumn = new TableColumn<>("Due time");
      dueTimeColumn.setCellValueFactory(cellData -> {
         Card card = cellData.getValue();
         Binding<String> binding = Bindings.createStringBinding(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime dueTime = LocalDateTime.parse(card.getDueTime());
            return dueTime.format(formatter);
         }, card.dueTimeProperty());
         return binding;
      });

      dueTimeColumn.setCellFactory(column -> {
         TableCell<Card, String> cell = new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty || item == null) {
                  setText(null);
                  setGraphic(null);
               } else {
                  this.setAlignment(Pos.CENTER_LEFT);
                  setText(item);
               }
            }
         };
         return cell;
      });
      TableColumn<Card, HBox> editButtonColumn = new TableColumn<>();
      addCardButton = new Button("Add");
      HBox box = new HBox(addCardButton);
      addCardButton.setOnAction(e -> {
         Card card = new Card();
         editCardDialog.addCard(card, controller);
         editCardDialog.showAndWait();
      });
      editButtonColumn.setGraphic(addCardButton);
      addCardButton.setAlignment(Pos.CENTER);
      

      editButtonColumn.setCellValueFactory(cellData -> {
         Button editButton = new Button("::");
         editButton.setOnAction(e -> {
            editCardDialog.setCard(cellData.getValue());
            editCardDialog.showAndWait();
         });

         Button deleteButton = new Button("X");
         deleteButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            controller.removeCard(cellData.getValue());
         });

         HBox bar = new HBox(deleteButton, editButton);
         bar.setSpacing(5);
         bar.setAlignment(Pos.CENTER);
         return new SimpleObjectProperty<>(bar);
      });

      editButtonColumn.setCellFactory(column -> {
         TableCell<Card, HBox> cell = new TableCell<>() {

            protected void updateItem(HBox hbox, boolean empty) {
               if(hbox == null || empty){
                  setText(null);
                  setGraphic(null);
               }else{
                  this.setAlignment(Pos.CENTER);
                  setGraphic(hbox);
               }
            }
         };
         return cell;
      });

      cardTable.getColumns().addAll(frontContentColumn, backContentColumn, dueTimeColumn, editButtonColumn);

      topicTable.getColumns().clear();

      TableColumn<StringProperty, String> topicColumn = new TableColumn<>(
            "Topic name");
      topicColumn.setCellValueFactory(cellData -> {
         return cellData.getValue();
      });

      topicColumn.setCellFactory(column -> {
         TableCell<StringProperty, String> cell = new TableCell<>() {

            @Override
            protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty || item == null) {
                  setText(null);
                  setGraphic(null);
               } else {
                  this.setAlignment(Pos.CENTER);
                  setText(item);
               }
            }
         };
         cell.setOnMouseClicked(e -> {
            if (cell.getItem() != null) {
               cardLibraryController.setCardsByTopic(cell.getItem());
            }
         });
         return cell;
      });

      topicTable.getColumns().add(topicColumn);
   }

   public <T> T getRoot() {
      return loader.getRoot();
   }

   private void disableScreen() {
      Parent root = getRoot();
      Scene scene = root.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.setOpacity(0);
   }

   private void showScreen() {
      Parent root = getRoot();
      Scene scene = root.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.setOpacity(1);
   }

   private TextInputDialog showEnterNameDialog(String preText) {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("New topic name");
      dialog.setHeaderText("Enter your topic name");
      dialog.setContentText("Name: ");
      dialog.getEditor().setText(preText);
      return dialog;
   }
}
