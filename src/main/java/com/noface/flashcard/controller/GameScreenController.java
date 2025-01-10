package com.noface.flashcard.controller;

import java.io.IOException;

import com.noface.flashcard.view.GameScreen;

public class GameScreenController {
    private GameScreen screen;
    public GameScreenController() throws IOException {
        screen = new GameScreen(this);
    }

    public GameScreen getScreen() {
        return screen;
    }
}
