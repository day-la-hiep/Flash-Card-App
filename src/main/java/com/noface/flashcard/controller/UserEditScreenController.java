package com.noface.flashcard.controller;

import java.io.IOException;

import com.noface.flashcard.model.User;
import com.noface.flashcard.utils.ResourceLoader;
import com.noface.flashcard.view.UserEditScreen;

public class UserEditScreenController {
    private UserEditScreen screen;
    private User user;
    public UserEditScreenController() throws IOException {
        user = ResourceLoader.getInstance().userCRUD.getUserInfo();
        screen = new UserEditScreen(this);
    }

    public UserEditScreen getScreen() {
        return screen;
    }

    public User getUser() {
        return user;
    }

    public int editUser(String newPassword) {
        int status = ResourceLoader.getInstance().userCRUD().editUser(user, newPassword);
        return status;
    }


}
