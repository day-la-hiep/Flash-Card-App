package com.noface.flashcard.userUtilities;

import java.io.IOException;

import com.noface.flashcard.model.User;
import com.noface.flashcard.screenNavigation.MainController;
import com.noface.flashcard.utils.ResourceLoader;

public class LoginRegisterController {
   private LoginScreen loginScreen;
   private RegisterScreen registerScreen;
   private MainController mainController;
   public void setMainController(MainController mainController){
      this.mainController = mainController;
   }

   public LoginRegisterController() throws IOException {
      loginScreen = new LoginScreen(this);
      registerScreen = new RegisterScreen(this);
      loginScreen.setRegisterScreen(registerScreen);
   }

   public boolean login(String username, String password) {
      String correctPassword = ResourceLoader.getInstance().getUserPassword(username);
      if(correctPassword == null){
         return false;
      }
      return correctPassword.equals(password);
   }

   public LoginScreen getLoginScreen() {
      return loginScreen;
   }

   public User createUser(String username, String password) {
      if(ResourceLoader.getInstance().getAllUsername().contains(username)){
         User user = new User(username, password);
         return user;
      }
      return null;
   }

   public void startSession(String username) {
      mainController.startSession(username);
   }
}
