package com.noface.flashcard.utils;

import com.noface.flashcard.model.User;

import java.io.*;

public class FileLoader {
    private void writeFile(User user, String pathFile) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(pathFile));
            objectOutputStream.reset();
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Erorr File");
        }
    }

    private User readUser(String pathFile) throws ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathFile));
            User userFile = (User) objectInputStream.readObject();
            objectInputStream.close();
            return userFile;
        } catch (IOException | ClassNotFoundException e) {
            throw new ClassNotFoundException(e.toString());
        }
    }
}
