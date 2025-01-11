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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
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

      renameButton.setOnAction(e -> {
         StringProperty topicProperty = topicTable.getSelectionModel().getSelectedItem();
         if (topicProperty != null) {
            try {
               TextInputDialog dialog = new TextInputDialog();
               dialog.setTitle("New topic name");
               dialog.setHeaderText("Enter your new topic name");
               dialog.setContentText("Name: ");
               dialog.getEditor().setText(topicProperty.get());
               Optional<String> result = dialog.showAndWait();

               result.ifPresent(name -> {
                  try {
                     cardLibraryController.renameCurrentTopicTo(name);
                     topicProperty.set(name);
                  } catch (Exception e2) {

                  }
               });
            } catch (Exception e1) {

            }
         }

      });

      deleteTopicButton.setOnAction(e -> {
         int topicIndex = topicTable.getSelectionModel().getFocusedIndex();
         cardLibraryController.removeCurrentTopic();
         cardLibraryController.setCardsByTopic(topicTable.getSelectionModel().getSelectedItem().get());
      });

   }

   public void bindingTableToController(CardLibraryController controller) {
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

      TableColumn<Card, Button> editButtonColumn = new TableColumn<>("");
      editButtonColumn.setPrefWidth(50);
      editButtonColumn.setResizable(false);
      editButtonColumn.setCellValueFactory(cellData -> {
         Button button = new Button("Edit");
         button.setMaxWidth(Double.MAX_VALUE);
         button.setMaxHeight(Double.MAX_VALUE);
         button.setOnAction(e -> {
            editCardDialog.setCard(cellData.getValue());
            Parent root = editCardDialog.getRoot();
            if (root.getScene() == null) {
               Scene scene = new Scene(root);
            }
            Stage stage = new Stage();
            stage.setScene(root.getScene());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
         });
         return new SimpleObjectProperty<>(button);
      });

      cardTable.getColumns().addAll(frontContentColumn, backContentColumn, dueTimeColumn, editButtonColumn);

      topicTable.getColumns().clear();

      TableColumn<StringProperty, String> topicColumn = new TableColumn<>("Topic name");
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
                  setText(item);  
              }
            }
         };
         cell.setOnMouseClicked(e -> {
            if(cell.getItem() != null){
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

   public void disableScreen() {
      Parent root = getRoot();
      Scene scene = root.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.setOpacity(0);
   }

   public void showScreen() {
      Parent root = getRoot();
      Scene scene = root.getScene();
      Stage stage = (Stage) scene.getWindow();
      stage.setOpacity(1);
   }
}
