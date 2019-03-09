package com.codecool.web.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {

    private Tweet tweet;

    @BeforeEach
    void setUp() {
        tweet = new Tweet(1, "Yoda", "Do. Or do not. There is no try.");
    }

    @AfterEach
    void tearDown() {
        tweet = null;
    }

    @Test
    void getId() {
        assertEquals(1, tweet.getId());
    }

    @Test
    void getPosterName() {
        assertEquals("Yoda", tweet.getPosterName());
    }

    @Test
    void getContent() {
        assertEquals("Do. Or do not. There is no try.", tweet.getContent());
    }

}
