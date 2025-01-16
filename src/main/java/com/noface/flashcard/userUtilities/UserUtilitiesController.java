package com.noface.flashcard.userUtilities;

import java.io.IOException;

import com.noface.flashcard.model.User;
import com.noface.flashcard.screenNavigation.MainController;
import com.noface.flashcard.utils.ResourceLoader;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UserUtilitiesController {
   private ObjectProperty<User> userProperty = new SimpleObjectProperty<>();
   private LoginScreen loginScreen;
   private RegisterScreen registerScreen;
   private ProfileScreen profileScreen;
   private MainController mainController;
   public void setMainController(MainController mainController){
      this.mainController = mainController;
   }

   public UserUtilitiesController() throws IOException {
      userProperty.bind(ResourceLoader.getInstance().getUserProperty());
      loginScreen = new LoginScreen(this);
      registerScreen = new RegisterScreen(this);
      profileScreen = new ProfileScreen(this);
      loginScreen.setRegisterScreen(registerScreen);
   }

   public boolean login(String username, String password) {
      return ResourceLoader.getInstance().authenticate(username, password);
   }

   public LoginScreen getLoginScreen() {
      return loginScreen;
   }

   public User createUser(String username, String password) throws IOException {
      if(ResourceLoader.getInstance().isValidUsername(username)){
         User user = new User(username, password);
         ResourceLoader.getInstance().createNewAccountData(username, password);
         return user;
      }
      return null;
   }
   public void saveUser() throws IOException{
      ResourceLoader.getInstance().updateUser();
   }

   public void startSession(String username) {
      mainController.startSession(username);
   }
   public ProfileScreen getProfileScreen(){
      return profileScreen;
   }
}
