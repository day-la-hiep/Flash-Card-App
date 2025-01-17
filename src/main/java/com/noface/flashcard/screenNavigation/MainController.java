package com.noface.flashcard.screenNavigation;

import java.io.IOException;

import com.noface.flashcard.cardLibrary.CardLibraryController;
import com.noface.flashcard.game.WordCombineGameController;
import com.noface.flashcard.userUtilities.UserUtilitiesController;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.stage.Stage;

public class MainController {
    private MainScreen mainScreen;
    private CardLibraryController cardLibraryController;
    private UserUtilitiesController loginRegisterController;
    private Stage mainStage;

    public MainController(CardLibraryController cardLibraryController, UserUtilitiesController loginRegisterController) throws IOException{
        mainScreen = new MainScreen(this);
        this.cardLibraryController = cardLibraryController;
        this.loginRegisterController = loginRegisterController;
        mainScreen.setLibraryScreen(cardLibraryController.getScreen());
        mainScreen.setLoginScreen(loginRegisterController.getLoginScreen());
        mainScreen.setProfileScreen(loginRegisterController.getProfileScreen());
        loginRegisterController.getLoginScreen().setMainScreen(mainScreen);
    }
    public void startSession(String username){
        ResourceLoader.getInstance().setCurrentUser(username);
        
    }
    public void endSession() {
        try {
            ResourceLoader.getInstance().updateCurrentUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResourceLoader.getInstance().setCurrentUser(null);
    }

    public CardLibraryController getCardLibraryController() {
        return cardLibraryController;
    }
    public void setCardLibraryController(CardLibraryController cardLibraryController) {
        this.cardLibraryController = cardLibraryController;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
        mainScreen.setMainStage(mainStage);
    }
    public MainScreen getMainScreen() {
        return mainScreen;
    }

}
