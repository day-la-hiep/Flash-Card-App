module com.noface.flashcard {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires java.net.http;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires org.slf4j;
    requires java.desktop;
   requires javafx.graphics;
    requires ch.qos.logback.core;

    opens com.noface.flashcard.cardLearning to javafx.fxml;
    opens com.noface.flashcard.userUtilities to javafx.fxml;
    opens com.noface.flashcard.cardLibrary to javafx.fxml;
    opens com.noface.flashcard.screenNavigation to javafx.fxml;
    opens com.noface.flashcard.wordCombineGame to javafx.fxml;
    opens com.noface.flashcard to javafx.fxml;
    opens com.noface.flashcard.game to javafx.fxml;

    exports com.noface.flashcard;
    exports com.noface.flashcard.model;
}