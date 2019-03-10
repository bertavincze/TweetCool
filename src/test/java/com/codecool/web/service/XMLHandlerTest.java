package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class XMLHandlerTest {

    private XMLHandler xmlHandler;
    private Tweet tweetYoda;
    private Tweet tweetDonald;

    @BeforeEach
    void setUp() {
        xmlHandler = new XMLHandler();
        tweetYoda = new Tweet(1, "Yoda", "Do. Or do not. There is no try.");
        tweetDonald = new Tweet(2, "Donald Trump", "Despite the negative press covfefe");
    }

    @AfterEach
    void tearDown() {
        xmlHandler = null;
        tweetYoda = null;
        tweetDonald = null;
    }

    @Test
    void saveAndLoad() throws TransformerException, ParseException, SAXException, IOException {
        xmlHandler.getTweetList().addTweet(tweetDonald);
        xmlHandler.getTweetList().addTweet(tweetYoda);
        xmlHandler.saveToFile("./test.xml");
        xmlHandler.loadFromFile("./test.xml");
        assertEquals(tweetDonald.getPosterName(), xmlHandler.getTweetList().getTweets().get(0).getPosterName());
    }

}
