package com.noface.flashcard.screenNavigation;

import java.io.IOException;

import com.noface.flashcard.cardLibrary.CardLibraryScreen;
import com.noface.flashcard.game.WordCombineGameController;
import com.noface.flashcard.userUtilities.LoginScreen;
import com.noface.flashcard.userUtilities.ProfileScreen;

import com.noface.flashcard.game.WordCombineGameScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
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

    private FXMLLoader loader;
    private MainController mainController;
    private CardLibraryScreen libraryScreen;
    private ProfileScreen profileScreen;
    private WordCombineGameScreen wordCombineGameScreen;

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


    public void setProfileScreen(ProfileScreen profileScreen){
        this.profileScreen = profileScreen;
    }
    
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
        gameButton.setOnAction(e -> {
            changeToGameScreen();
        });
    }

    public void changeToGameScreen(){
        WordCombineGameController controller = null;
        try{
            controller = new WordCombineGameController(this.mainController.getCardLibraryController());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) controller.getScreen().getRoot());
    }
    public void changeToLibraryScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) libraryScreen.getRoot());
    }
    private void changeToLoginScreen(){
        rightPane.getChildren().clear();
        Parent loginScreenRoot = loginScreen.getRoot();
        mainStage.setScene(loginScreenRoot.getScene());
    }

    private void changeToProfileScreen(){
        rightPane.getChildren().clear();
        rightPane.getChildren().add((Node) profileScreen.getRoot());
        profileScreen.changeToDefaultStatus();
    }

    public WordCombineGameScreen getWordCombineGameScreen() {
        return wordCombineGameScreen;
    }

    public void setWordCombineGameScreen(WordCombineGameScreen wordCombineGameScreen) {
        this.wordCombineGameScreen = wordCombineGameScreen;
    }

    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
}
