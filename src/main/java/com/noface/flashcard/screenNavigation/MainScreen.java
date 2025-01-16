package com.noface.flashcard.screenNavigation;

import java.io.IOException;

import com.noface.flashcard.cardLibrary.CardLibraryScreen;
import com.noface.flashcard.userUtilities.LoginScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreen {
    private FXMLLoader loader;
    public MainScreen(MainController mainController) throws IOException {
        loader = new FXMLLoader(this.getClass().getResource("MainScreen.fxml"));
        loader.setController(this);
        loader.load();
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

    @FXML
    private Button gameButton;

    @FXML
    private StackPane imageContainer;

    @FXML
    private VBox leftVBox;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private Button topicButton;
    
    
    public <T> T getRoot() {
        return loader.getRoot();
    }

    public void changeToLibraryScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) libraryScreen.getRoot());
    }
    public void changeToLogoutScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) loginScreen.getRoot());
    }
    
}
