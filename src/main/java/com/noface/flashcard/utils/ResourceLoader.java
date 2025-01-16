package com.noface.flashcard.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.noface.flashcard.model.User;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ResourceLoader {
    private ObjectProperty<User> userProperety;
    private final String userLoginInfoPath = "account-data/login-info.dat";
    private final String userDataDir = "data/";
    private Map<String, String> userPasswords = new HashMap<>();
    private static ResourceLoader resourceLoader;
    FileLoader fileLoader = new FileLoader();

    public static ResourceLoader getInstance() {
        if (resourceLoader == null) {
            resourceLoader = new ResourceLoader();
            resourceLoader.userProperety = new SimpleObjectProperty<>();
            try {
                resourceLoader.readUserLoginInfo();
                System.out.println("So luong user: " + resourceLoader.userPasswords.size());
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return resourceLoader;
    }

    public void saveAllData() {

    }

    @SuppressWarnings("unchecked")
    private void readUserLoginInfo() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userLoginInfoPath));
        userPasswords = (Map<String, String>) ois.readObject();
        ois.close();
    }

    public void saveUserData(User user) {
        fileLoader.writeFile(user, userDataDir + user.getUsername());
    }

    private User getUserData(String username) {
        try {
            User user = fileLoader.readUser(userDataDir + username);
            return user;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createNewAccountData(String username, String password) throws IOException {
        userPasswords.put(username, password);
        User user = new User(username, password);
        saveUserData(user);
        updateUserPasswordToFile();
    }

    private void updateUserPasswordToFile() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userLoginInfoPath));
        oos.writeObject(userPasswords);
        oos.close();
    }

    private void updateUserPassword(String username, String password) throws IOException {
        userPasswords.put(username, password);
        updateUserPasswordToFile();
    }

    public boolean isValidUsername(String username) {
        for (String currentName : userPasswords.keySet()) {
            if (username.equals(currentName)) {
                return false;
            }
        }

        return true;
    }
    public boolean authenticate(String username, String password){
        if(!userPasswords.keySet().contains(username)){
            return false;
        }
        return userPasswords.get(username).equals(password);
    }

    public void updateUser() throws IOException {
        saveUserData(userProperety.get());
        updateUserPassword(userProperety.get().getUsername(), userProperety.get().getPassword());
    }
    public ObjectProperty<User> getUserProperty(){
        return userProperety;
    }
    public void setCurrentUser(String username){
        userProperety.set(getUserData(username));
    }
}
