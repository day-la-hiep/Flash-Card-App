package com.noface.flashcard.userUtilities;

import java.io.IOException;

import com.noface.flashcard.model.User;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ProfileScreen {
    private FXMLLoader loader;
    private UserUtilitiesController controller;
    private ObjectProperty<User> userProperty;

    @FXML
    private Button cancelButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private DatePicker dobTF;

    @FXML
    private Button editButton;

    @FXML
    private HBox editChangePasswordBar;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField genderTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField phoneNumberTF;

    @FXML
    private Button saveButton;

    @FXML
    private HBox saveCancelBar;

    public ProfileScreen(UserUtilitiesController controller) throws IOException {
        userProperty = new SimpleObjectProperty<>();
        userProperty.bind(controller.getUserProperty());
        this.controller = controller;
        loader = new FXMLLoader(getClass().getResource("ProfileScreen.fxml"));
        loader.setController(this);
        loader.load();
    }

    private void updateScreenValue() {
        System.out.println("This is update screen value");
        nameTF.setText(userProperty.get().getName());
        dobTF.setValue(userProperty.get().getDob());
        phoneNumberTF.setText(userProperty.get().getPhoneNumber());
        emailTF.setText(userProperty.get().getEmail());
        genderTF.setText(userProperty.get().getGender());
    }

    public void changeToDefaultStatus() {
        updateScreenValue();
        saveCancelBar.setVisible(false);
        editChangePasswordBar.setVisible(true);
        nameTF.setEditable(false);
        dobTF.setEditable(false);
        phoneNumberTF.setEditable(false);
        emailTF.setEditable(false);
        genderTF.setEditable(false);
    }

    public void changeToEditUserPropertyStatus() {
        saveCancelBar.setVisible(true);
        editChangePasswordBar.setVisible(false);
        nameTF.setEditable(true);
        dobTF.setEditable(true);
        phoneNumberTF.setEditable(true);
        emailTF.setEditable(true);
        genderTF.setEditable(true);
    }

    public <T> T getRoot() {
        return loader.getRoot();
    }

    @FXML
    public void initialize() {
        userProperty.addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    updateScreenValue();
                }
            }
        });

        saveButton.setOnAction(e -> {

            try {
                controller.updateUserInfo(nameTF.getText(), dobTF.getValue(),
                        emailTF.getText(), genderTF.getText(), phoneNumberTF.getText());
                controller.saveUser();
                changeToDefaultStatus();
                updateScreenValue();
            } catch (IOException e1) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Information is not valid");
                alert.show();
                e1.printStackTrace();
            }
        });
        cancelButton.setOnAction(e -> {
            changeToDefaultStatus();
            updateScreenValue();
        });

        editButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.showAndWait();
            changeToEditUserPropertyStatus();
        });

        changePasswordButton.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();

            ButtonType changeButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

            PasswordField newPasswordField = new PasswordField();
            newPasswordField.setPromptText("New Password");
            PasswordField confirmPasswordField = new PasswordField();
            confirmPasswordField.setPromptText("Confirm New Password");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            grid.add(new Label("New Password:"), 0, 1);
            grid.add(newPasswordField, 1, 1);
            grid.add(new Label("Confirm Password:"), 0, 2);
            grid.add(confirmPasswordField, 1, 2);

            dialog.getDialogPane().setContent(grid);

            Node changeButton = dialog.getDialogPane().lookupButton(changeButtonType);
            changeButton.setDisable(true);

            newPasswordField.textProperty().addListener((obs, oldValue, newValue) -> {
                changeButton.setDisable(newValue.trim().isEmpty() ||
                        !newValue.equals(confirmPasswordField.getText()));
            });

            confirmPasswordField.textProperty().addListener((obs, oldValue, newValue) -> {
                changeButton.setDisable(!newValue.equals(newPasswordField.getText()));
            });

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == changeButtonType) {
                    if(newPasswordField.getText() != ""){
                        controller.updateUserPassword(newPasswordField.getText());
                    }
                }
                return null;
            });
            dialog.show();
        });
    }
}
