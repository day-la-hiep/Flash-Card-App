package com.noface.flashcard.screenNavigation;

import java.io.IOException;

import com.noface.flashcard.cardLibrary.CardLibraryScreen;
import com.noface.flashcard.userUtilities.LoginScreen;
import com.noface.flashcard.userUtilities.ProfileScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
    private FXMLLoader loader;
    private MainController mainController;
    public MainScreen(MainController mainController) throws IOException {
        loader = new FXMLLoader(this.getClass().getResource("MainScreen.fxml"));
        loader.setController(this);
        loader.load();
        this.mainController = mainController;
    }

    private LoginScreen loginScreen;
    public LoginScreen getLoginScreen() {
        return loginScreen;
    }


    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }


    public CardLibraryScreen getLibraryScreen() {
        return libraryScreen;
    }


    public void setLibraryScreen(CardLibraryScreen libraryScreen) {
        this.libraryScreen = libraryScreen;
    }

    private CardLibraryScreen libraryScreen;

    private ProfileScreen profileScreen;

    public void setProfileScreen(ProfileScreen profileScreen){
        this.profileScreen = profileScreen;
    }



    @FXML
    private Button gameButton;

    @FXML
    private VBox leftVBox;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Button libraryButton;
    
    
    public <T> T getRoot() {
        return loader.getRoot();
    }
    @FXML
    public void initialize(){
        logoutButton.setOnAction(e -> {
            mainController.endSession();
            changeToLoginScreen();
        });
        libraryButton.setOnAction(e -> {
            changeToLibraryScreen();
        });
        profileButton.setOnAction(e -> {
            changeToProfileScreen();
        });
    }

    public void changeToLibraryScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) libraryScreen.getRoot());
    }
    public void changeToLoginScreen(){
        rightPane.getChildren().clear();
        Parent loginScreenRoot = loginScreen.getRoot();
        mainStage.setScene(loginScreenRoot.getScene());
    }

    public void changeToProfileScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) profileScreen.getRoot());
    }

    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
}
