package com.noface.flashcard.userUtilities;

import java.io.IOException;

import com.noface.flashcard.screenNavigation.MainScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreen {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    private UserUtilitiesController controller;
    private RegisterScreen registerScreen;

    private FXMLLoader loader;
        private MainScreen mainScreen;
        public LoginScreen(UserUtilitiesController controller) throws IOException {
            loader = new FXMLLoader(this.getClass().getResource("LoginScreen.fxml"));
            System.out.println(loader);
            loader.setController(this);
            loader.load();
            this.controller = controller;
        }
        @FXML
        public void initialize(){
            loginButton.setOnAction(e -> {
              boolean isSuccess = controller.login(usernameField.getText(), passwordField.getText());
              if(isSuccess == false){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Invalid account, please try again");
                alert.showAndWait();
                refreshScreen();
              }else{
                controller.startSession(usernameField.getText());
                Scene scene = loginButton.getScene();
                Stage stage = (Stage) scene.getWindow();
                Parent mainScreenRoot = mainScreen.getRoot();
                if(mainScreenRoot.getScene() == null){
                    Scene rootScene = new Scene(mainScreenRoot);
                }
                mainScreen.changeToLibraryScreen();
                stage.setScene(mainScreenRoot.getScene());
              }
            });
    
            registerButton.setOnAction(e -> {
                Parent root = registerScreen.getRoot();
                if(root.getScene() == null){
                    Scene scene = new Scene(root);
                }
    
                Stage stage = new Stage();
                stage.setScene(root.getScene());
                stage.show();
            });
        }
    
        public void refreshScreen() {
            usernameField.setText("");
            passwordField.setText("");
        }
    
        public PasswordField getPasswordField() {
            return passwordField;
        }
    
        public TextField getUsernameField() {
            return usernameField;
        }
    
        public <T> T getRoot(){
            return loader.getRoot();
        }
    
        public void setRegisterScreen(RegisterScreen registerScreen) {
            this.registerScreen = registerScreen;
        }
    
    
    
    
        public void setMainScreen(MainScreen mainScreen) {
            this.mainScreen = mainScreen;
    }

}
