package com.noface.flashcard.controller;

import java.io.IOException;

import com.noface.flashcard.view.TranslateScreen;

public class TranslateScreenController {
    private TranslateScreen screen;
    public TranslateScreenController() throws IOException {
        screen = new TranslateScreen();
    }

    public TranslateScreen getScreen() {
        return screen;
    }
}
