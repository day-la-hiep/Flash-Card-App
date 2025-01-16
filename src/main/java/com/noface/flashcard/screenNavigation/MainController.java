package com.noface.flashcard.screenNavigation;

import java.io.IOException;

import com.noface.flashcard.cardLibrary.CardLibraryController;
import com.noface.flashcard.model.User;
import com.noface.flashcard.userUtilities.LoginRegisterController;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private MainScreen mainScreen;
    private CardLibraryController cardLibraryController;
    private LoginRegisterController loginRegisterController;
    private Stage mainStage;

    public CardLibraryController getCardLibraryController() {
        return cardLibraryController;
    }
    public void setCardLibraryController(CardLibraryController cardLibraryController) {
        this.cardLibraryController = cardLibraryController;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public MainScreen getMainScreen() {
        return mainScreen;
    }
    public MainController(CardLibraryController cardLibraryController, LoginRegisterController loginRegisterController) throws IOException{
        mainScreen = new MainScreen(this);
        this.cardLibraryController = cardLibraryController;
        this.loginRegisterController = loginRegisterController;
        mainScreen.setLibraryScreen(cardLibraryController.getScreen());
        mainScreen.setLoginScreen(loginRegisterController.getLoginScreen());
    }
    public void startSession(String username){
        User user = ResourceLoader.getInstance().getUserData(username);
        cardLibraryController.loadData(user);
        Parent root = mainScreen.getRoot();
        if(root.getScene() == null){
            Scene scene =  new Scene(root);
        }
        mainStage.setScene(root.getScene());
        mainScreen.changeToLibraryScreen();
    }

}
