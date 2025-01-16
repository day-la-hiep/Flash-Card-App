package com.noface.flashcard.userUtilities;

import java.io.IOException;

import com.noface.flashcard.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RegisterScreen {

    @FXML
    TextField name;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField confirmPassword;
    @FXML
    TextField day;
    @FXML
    TextField month;
    @FXML
    TextField year;
    @FXML
    ChoiceBox<String> gender = new ChoiceBox<>();
    @FXML
    Button registerButton;
    @FXML
    TextField email;
    @FXML
    TextField phone;

    private FXMLLoader loader;
    private LoginRegisterController controller;
    public RegisterScreen(LoginRegisterController loginRegisterController) throws IOException {
        loader = new FXMLLoader(this.getClass().getResource("RegisterScreen.fxml"));
        loader.setController(this);
        loader.load();
        controller = loginRegisterController;
    }

    public <T> T getRoot(){
        return loader.getRoot();
    }

    @FXML
    public void initialize(){
        registerButton.setOnAction(e -> {
            User user = null;
            try {
                user = controller.createUser(username.getText(), password.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if(user == null){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Invalid information for account");
                alert.show();
            }else{
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
