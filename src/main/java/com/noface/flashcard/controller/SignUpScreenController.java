package com.noface.flashcard.controller;

import java.io.IOException;

import com.noface.flashcard.view.SignUpScreen;

public class SignUpScreenController {
    private SignUpScreen screen;
    public SignUpScreenController() throws IOException {
        screen = new SignUpScreen(this);
    }

    public SignUpScreen getScreen() {
        return screen;
    }
}
