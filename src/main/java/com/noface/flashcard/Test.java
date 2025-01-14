package com.noface.flashcard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.noface.flashcard.model.Card2;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test {
//    public static void main1(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode jsonNode = objectMapper.createObjectNode();
//        jsonNode.put("name", "Abul Hasan");
//        jsonNode.put("age", 23);
//        jsonNode.put("city", "Lucknow");
//        jsonNode.put("state", "Uttar Pradesh");
//        jsonNode.put("country", "India");
//        objectMapper.writeValue(new File("mydata.json"), jsonNode);
//        System.out.println("completed");
//    }

    public static void main(String[] args) throws IOException, URISyntaxException {
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

        for(Card2 card2 : card2List){
            System.out.println(card2.getFront() + "\n" + card2.getBack() + "\n" + card2.getTopic());
        }
    }
}
