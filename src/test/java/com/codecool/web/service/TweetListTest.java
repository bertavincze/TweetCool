package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TweetListTest {

    private TweetList tweetList;
    private Tweet tweetYoda;
    private Tweet tweetDonald;

    @BeforeEach
    void setUp() {
        tweetList = new TweetList();
        tweetYoda = new Tweet(1, "Yoda", "Do. Or do not. There is no try.");
        tweetDonald = new Tweet(2, "Donald Trump", "Despite the negative press covfefe");
        tweetList.addTweet(tweetDonald);
    }

    @AfterEach
    void tearDown() {
        tweetList = null;
        tweetDonald = null;
        tweetYoda = null;
    }

    @Test
    void addTweet() {
        tweetList.addTweet(tweetYoda);
        assertTrue(tweetList.getTweets().contains(tweetYoda));
    }

    @Test
    void getTweets() {
        assertEquals(Collections.singletonList(tweetDonald), tweetList.getTweets());
    }
}
