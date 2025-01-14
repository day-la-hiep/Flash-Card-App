package com.noface.flashcard.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noface.flashcard.model.Card2;
import org.json.JSONArray;
import org.json.JSONObject;

import com.noface.flashcard.FXMain;
import com.noface.flashcard.view.WordCombineGameScreen;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class WordCombineGameController {
    private WordCombineGameScreen screen;
    private ListProperty<Card2> words = new SimpleListProperty(FXCollections.observableArrayList());

    public WordCombineGameController() throws IOException {

        try {
            words.get().clear();
            getWordsData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        screen = new WordCombineGameScreen(this);
    }

    public List<String> extractWordFromJson(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        List<String> words = new ArrayList<String>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.has("word")) {
                String word = obj.getString("word");
                if(word.length() <= 5){
                    words.add(obj.getString("word"));
                }
            }
        }

        return words;
    }
    public String sendApiRequestToDICT_HHDB(String text, String translatePath) throws Exception {
        text = text.toLowerCase();

        String encodedText = java.net.URLEncoder.encode(text, StandardCharsets.UTF_8);
        String urlStr = String.format("http://localhost:8080/%s/byWord/%s", translatePath, encodedText);
        System.out.println(urlStr);
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            return extractHtmlFromJson(response.toString());
        } finally {
            conn.disconnect();
        }
    }

    private String extractHtmlFromJson(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        StringBuilder htmlBuilder = new StringBuilder();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.has("html")) {
                htmlBuilder.append(obj.getString("html")).append("<br>");
            }
        }
        return htmlBuilder.toString();
    }


    public WordCombineGameScreen getScreen() {
        return screen;
    }


    public void getWordsData() throws URISyntaxException {
        URL resourceUrl = FXMain.class.getResource("card.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        List<Card2> card2List = new ArrayList<>();
        try{
            jsonNode = objectMapper.readTree(new File(resourceUrl.toURI()));
            TypeReference<List<Card2>> card2TypeReference = new TypeReference<List<Card2>>() {};
            card2List = objectMapper.readValue(resourceUrl, card2TypeReference);
        } catch (Exception e) {
            System.out.println("Card file not found!!!");
        }

        words.addAll(card2List);
    }

    public ObservableList<Card2> getWords() {
        return words.get();
    }

    public ListProperty<Card2> wordsProperty() {
        return words;
    }

    public void refresh() {
    }
}
