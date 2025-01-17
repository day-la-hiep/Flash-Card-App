package com.noface.flashcard.userUtilities;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.noface.flashcard.model.User;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterScreen {

    @FXML
    private ChoiceBox<String> genderTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phone;
    @FXML
    private Button registerButton;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private DatePicker dobTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private PasswordField confirmPasswordTF;
    private FXMLLoader loader;
    private UserUtilitiesController controller;

    public RegisterScreen(UserUtilitiesController loginRegisterController) throws IOException {
        loader = new FXMLLoader(this.getClass().getResource("RegisterScreen.fxml"));
        loader.setController(this);
        loader.load();
        controller = loginRegisterController;
    }

    public <T> T getRoot() {
        return loader.getRoot();
    }

    @FXML
    public void initialize() {
        genderTF.setItems(FXCollections.observableArrayList());
        genderTF.getItems().addAll("Male", "Female", "Other");
        registerButton.setOnAction(e -> {
            String name = nameTF.getText();
            String username = usernameTF.getText();
            String phoneNumber = phoneNumberTF.getText();
            LocalDate dob = dobTF.getValue();
            String password = passwordTF.getText();
            String confirmPassword = confirmPasswordTF.getText();
            String gender = genderTF.selectionModelProperty().get().getSelectedItem();
            String email = emailTF.getText();
            if (username.equals("") || name.equals("") || password.equals("") || email.equals("")) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Invalid information");
                alert.show();
            }
            if (password.equals(confirmPassword) == false) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Password not match, please try again");
                alert.show();
            }
            User user = new User(name, username, confirmPassword, dob, password, gender, phoneNumber, new HashMap<>());
            try {
                user = controller.createUser(user);
            } catch (IOException e1) {
                user = null;
            }
            if (user == null) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Invalid information for account");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Create user successfully");
                alert.show();
                Scene scene = registerButton.getScene();
                Stage stage = (Stage) scene.getWindow();
                stage.close();
            }
        });
    }

}
