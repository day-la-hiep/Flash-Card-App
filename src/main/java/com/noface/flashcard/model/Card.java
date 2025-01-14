package com.noface.flashcard.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card implements Serializable {
    private transient StringProperty dueTime = new SimpleStringProperty();
    private transient StringProperty frontContent = new SimpleStringProperty();
    private transient StringProperty backContent = new SimpleStringProperty();

    public Card(String frontContent, String backContent, String dueTime) {
        this.frontContent.set(frontContent);
        this.backContent.set(backContent);
        this.dueTime.set(dueTime);
    }

    public Card() {
        this.backContent.set("");
        this.frontContent.set("");
        this.dueTime.set(LocalDateTime.now().toString());
    }

    @Override
    public String toString() {
        return "Card{" +
                ", dueTime=" + dueTime.get() +
                ", frontContent=" + frontContent.get() +
                ", backContent=" + backContent.get() +
                '}';
    }

    public static Comparator<Card> comparatorByDueTimeNearest() {
        return new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return compareDate(o1, o2);
            }
        };
    }

    public static int compareDate(Card o1, Card o2) {
        return LocalDateTime.parse(
                o1.dueTime.get()).compareTo(LocalDateTime.parse(o2.dueTime.get()));
    }


    public String getDueTime() {
        return dueTime.get();
    }

    public StringProperty dueTimeProperty() {
        return dueTime;
    }

    public String getFrontContent() {
        return frontContent.get();
    }

    public StringProperty frontContentProperty() {
        return frontContent;
    }

    public String getBackContent() {
        return backContent.get();
    }

    public StringProperty backContentProperty() {
        return backContent;
    }

    public void setDueTime(String dueTime) {
        this.dueTime.set(dueTime);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeUTF(frontContent.get());
        oos.writeUTF(backContent.get());
        oos.writeUTF(dueTime.get());
    }
    private void readObject(ObjectInputStream ois) throws IOException{
        frontContent = new SimpleStringProperty(ois.readUTF());
        backContent = new SimpleStringProperty(ois.readUTF());
        dueTime = new SimpleStringProperty(ois.readUTF());
    }
}
