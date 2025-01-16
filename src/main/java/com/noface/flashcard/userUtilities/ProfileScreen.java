package com.noface.flashcard.userUtilities;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfileScreen {
    private FXMLLoader loader;
    private UserUtilitiesController controller;
    @FXML
    private TextField day;

    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField email;

    @FXML
    private TextField gender;

    @FXML
    private TextField month;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField year;

    public ProfileScreen(UserUtilitiesController controller) throws IOException {
        this.controller = controller;
        loader = new FXMLLoader(getClass().getResource("ProfileScreen.fxml"));
        loader.load();
        loader.setController(this);
    }

    public <T> T getRoot() {
        return loader.getRoot();
    }

    @FXML
    public void initialize(){
        saveButton.setOnAction(e -> {
            try {
                controller.saveUser();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
